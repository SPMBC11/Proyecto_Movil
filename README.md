 CritiChord

¡Bienvenido al repositorio de **CritiChord**! 

Este proyecto es una aplicación móvil desarrollada con **Android Studio** y **Jetpack Compose**. CritiChord es una herramienta para la crítica musical que permite a los usuarios evaluar, reseñar y descubrir música.

---

## 🎵 ¿Qué es CritiChord?

El nombre **CritiChord** es una combinación de "Criti" (de crítico) y "Chord" (de acorde). Refleja la idea de una herramienta precisa y afinada, ideal para evaluar o analizar música de manera eficiente. Elegimos este nombre porque es corto, fácil de recordar y tiene una sonoridad que se queda en la mente. Además, evoca la idea de una crítica musical enfocada, como un acorde perfecto en una composición.

---

## ✨ Características Principales

CritiChord es una aplicación que permite a los clientes:
* **Registrarse** en la aplicación.
* **Iniciar sesión**.
* **Recuperar su contraseña**.
* **Evaluar los álbumes** de 0 a 100.
* **Buscar un álbum** y consultar el equipo de producción.
* **Dejar y editar reseñas** de un álbum o canción.
* **Guardar una canción o álbum** en una lista.
* **Ver las reseñas y calificaciones** de los amigos.
* **Saber en qué plataformas están disponibles** los álbumes o las canciones.
* **Ver el promedio de calificación** de un álbum/canción.
* **Ver la trayectoria musical completa** de un artista.
* **Ver recomendaciones** de artistas y canciones.
* **Editar su perfil**.
* **Seguir cualquier artista** o productos de una canción o álbum.

---

## 🎨 Diseño y Paleta de Colores

La aplicación utiliza una paleta de colores específica para su diseño:

| Color | Código HEX | Uso |
| :--- | :--- | :--- |
| **Cian brillante / Turquesa** | `#00BCD4` | Fondo superior y acentos vibrantes. |
| **Negro puro** | `#000000` | Fondo del mensaje y botón. |
| **Blanco puro** | `#FFFFFF` | Texto principal. |
| **Azul denim claro** | `#6CAED0` | Otros fondos. |

El diseño de las interfaces de usuario se puede ver en las **cuatro pantallas** de Figma.

---

## 📁 Estructura del proyecto

El proyecto sigue una estructura estándar de Android, organizada para facilitar el desarrollo con Jetpack Compose.

```text
spmbc11-proyecto_movil/
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/
│       │   │   └── com/
│       │   │       └── example/
│       │   │           └── proyecto_movil/
│       │   │               ├── MainActivity.kt
│       │   │               ├── data/
│       │   │               │   └── local/
│       │   │               │       ├── Catalog.kt
│       │   │               │       ├── FalseAlbumProfRepository.kt
│       │   │               │       ├── FalseProfileInfoRepository.kt
│       │   │               │       ├── FalseReviewProfRepository.kt
│       │   │               │       ├── FalseReviewRepository.kt
│       │   │               │       ├── ProfileInfo.kt
│       │   │               │       └── ReviewInfo.kt
│       │   │               ├── navigation/
│       │   │               │   ├── NavGraph.kt
│       │   │               │   └── Screen.kt
│       │   │               ├── screen/
│       │   │               │   ├── addReviewScreen.kt
│       │   │               │   ├── albumReviewScreen.kt
│       │   │               │   ├── ArtistPage.kt
│       │   │               │   ├── Background.kt
│       │   │               │   ├── Content.kt
│       │   │               │   ├── ContentUser.kt
│       │   │               │   ├── EditarPerfil.kt
│       │   │               │   ├── HomePageScreen.kt
│       │   │               │   ├── ListScreen.kt
│       │   │               │   ├── LogInScreen.kt
│       │   │               │   ├── RegisterScreen2.kt
│       │   │               │   ├── settingsScreen.kt
│       │   │               │   ├── SingInScreen.kt
│       │   │               │   ├── UserProfileScreen.kt
│       │   │               │   └── welcomeScreen.kt
│       │   │               ├── ui/
│       │   │               │   └── theme/
│       │   │               │       ├── Color.kt
│       │   │               │       ├── Theme.kt
│       │   │               │       └── Type.kt
│       │   │               └── utils/
│       │   │                   ├── AlbumScreen.kt
│       │   │                   ├── AppButton.kt
│       │   │                   ├── AppLogo.kt
│       │   │                   ├── Componentes.kt
│       │   │                   ├── SocialLoginButton.kt
│       │   │                   ├── UserReview.kt
│       │   │                   └── recursos/
│       │   │                       ├── AlbumUI.kt
│       │   │                       └── ArtistUI.kt
│       │   └── res/
│       │       ├── drawable/
│       │       ├── mipmap-anydpi-v26/
│       │       ├── mipmap-hdpi/
│       │       ├── mipmap-mdpi/
│       │       ├── mipmap-xhdpi/
│       │       ├── mipmap-xxhdpi/
│       │       ├── mipmap-xxxhdpi/
│       │       ├── values/
│       │       └── xml/
│       └── test/
└── (otros archivos y directorios de Gradle)
```

* `data/`: Contiene los repositorios y modelos de datos.
* `navigation/`: Define las rutas y la navegación entre pantallas.
* `screen/`: Aloja las diferentes pantallas de la aplicación.
* `ui/`: Contiene los temas, colores y tipografía de la interfaz de usuario.
* `utils/`: Incluye componentes reutilizables y utilidades.
* `res/`: Contiene todos los recursos, como imágenes, strings y layouts.

---

## 💻 Desarrollo

Este proyecto fue elaborado por un equipo de estudiantes:
* Carlos Rojas Martinez
* Juan Francisco Vargas
* Santiago Pineda Mora
* Santiago Hernandez
* Juan Francisco Guzman

**Docente:** Juan Angarita Torres
**Año:** 2025

---

## 🚀 Cómo Empezar

1.  Clona el repositorio en tu máquina local.
2.  Abre el proyecto en **Android Studio Giraffe** o una versión posterior.
3.  Sincroniza el proyecto con los archivos de Gradle.
4.  Ejecuta la aplicación en un emulador o dispositivo físico.
