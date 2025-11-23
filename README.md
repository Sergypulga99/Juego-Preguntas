    # QACard - Android (Kotlin)


    ## Qué incluye

- Proyecto Android en Kotlin con una pantalla simple.
- Dos botones: "Nueva pregunta" y "Mostrar respuesta".
- Archivo de ejemplo con preguntas en `app/src/main/assets/questions.json` (20 QAs de ejemplo).

## Cómo obtener el APK (sin instalar Android Studio en tu teléfono)

### Opción A — Usar Codemagic (servicio online que compila APK desde un repositorio Git)
1. Crea una cuenta en https://codemagic.io/ y conéctala a tu repositorio (GitHub/GitLab/Bitbucket).
2. Sube este proyecto a un nuevo repositorio.
3. En Codemagic, crea una nueva aplicación y selecciona el repositorio y la rama.
4. Selecciona Android build, deja las opciones por defecto y lanza la compilación.
5. Cuando la compilación termine, descarga el artefacto APK desde la sección de artefactos.

### Opción B — Pedir a alguien con PC que abra el proyecto en Android Studio y genere el APK

### Opción C — Instalar Android Studio en un ordenador y abrir este proyecto.

## Reemplazar preguntas
Sustituye `app/src/main/assets/questions.json` por tu archivo JSON con tus 100 preguntas. El formato debe ser una lista de objetos con `pregunta` y `respuesta`.

Ejemplo:
```
[
  {"pregunta":"...","respuesta":"..."},
  ...
]
```


