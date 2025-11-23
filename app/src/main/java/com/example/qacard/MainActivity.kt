package com.example.qacard

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.io.BufferedReader
import kotlin.random.Random

data class QA(val pregunta: String, val respuesta: String)

class MainActivity : AppCompatActivity() {

    private lateinit var cardText: TextView
    private lateinit var btnNueva: Button
    private lateinit var btnRespuesta: Button

    private var qas: MutableList<QA> = mutableListOf()
    private var currentIndex: Int = -1
    private var showingAnswer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardText = findViewById(R.id.cardText)
        btnNueva = findViewById(R.id.btnNueva)
        btnRespuesta = findViewById(R.id.btnRespuesta)

        loadQuestions()

        btnNueva.setOnClickListener {
            nextQuestion()
        }

        btnRespuesta.setOnClickListener {
            showAnswer()
        }

        // restore state if available
        savedInstanceState?.let {
            currentIndex = it.getInt("currentIndex", -1)
            showingAnswer = it.getBoolean("showingAnswer", false)
            if (currentIndex != -1 && currentIndex < qas.size) {
                updateCard()
            } else {
                cardText.text = "Pulsa 'Nueva pregunta' para empezar"
            }
        } ?: run {
            cardText.text = "Pulsa 'Nueva pregunta' para empezar"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
        outState.putBoolean("showingAnswer", showingAnswer)
    }

    private fun loadQuestions() {
        try {
            val input = assets.open("questions.json")
            val text = BufferedReader(input.reader()).use { it.readText() }
            val arr = JSONArray(text)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                qas.add(QA(obj.getString("pregunta"), obj.getString("respuesta")))
            }
        } catch (e: Exception) {
            qas = mutableListOf()
            qas.add(QA("Error cargando preguntas", "Comprueba el archivo assets/questions.json"))
        }
    }

    private fun nextQuestion() {
        if (qas.isEmpty()) {
            cardText.text = "No hay preguntas disponibles."
            return
        }
        if (qas.size == 1) {
            currentIndex = 0
        } else {
            // select random index different from currentIndex
            var idx = Random.nextInt(qas.size)
            var tries = 0
            while (idx == currentIndex && tries < 20) {
                idx = Random.nextInt(qas.size)
                tries++
            }
            currentIndex = idx
        }
        showingAnswer = false
        updateCard()
    }

    private fun showAnswer() {
        if (currentIndex == -1) {
            cardText.text = "Primero pulsa 'Nueva pregunta'"
            return
        }
        showingAnswer = true
        updateCard()
    }

    private fun updateCard() {
        val item = qas.getOrNull(currentIndex)
        if (item == null) {
            cardText.text = "No hay más preguntas."
            return
        }
        cardText.text = if (showingAnswer) item.respuesta else item.pregunta
    }
}
