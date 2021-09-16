# MyPocketShop

## Tecnologias

* [Android JetPack](https://developer.android.com/jetpack?hl=es)
* [Compose](https://developer.android.com/jetpack/compose?hl=es)
* [Hilt y Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt-navigation)
* [Compose Material design icons - Core](https://mvnrepository.com/artifact/androidx.compose.material/material-icons-core?repo=google)
* [Compose Material design icons - Extended](https://mvnrepository.com/artifact/androidx.compose.material/material-icons-extended?repo=google)
* [Hilt docs](https://dagger.dev/hilt/)
* [RoomDataBase](https://developer.android.com/jetpack/androidx/releases/room)
* [DataStore](https://developer.android.com/topic/libraries/architecture/datastore?gclid=Cj0KCQjw1ouKBhC5ARIsAHXNMI-UnJFCj2bm9v-DPHj2A6KDCJUgv-_BcERBh3ptn4sx_ELRC_QSs1IaAiYZEALw_wcB&gclsrc=aw.ds)

## Estructura del proyecto

```batch
📦 mypocketshop
 ┣ 📂ui
 ┃ ┣ 📂components
 ┃ ┣ 📂screen
 ┃ ┃ ┣ 📂menu
 ┃ ┃ ┣ 📂products
 ┃ ┃ ┣ 📂settings
 ┃ ┃ ┗ 📂splash
 ┃ ┣ 📂theme
 ┃ ┗ 📂utils
 ┗ 📜MainActivity.kt
```
## Pantallas

![Splash Screen](/screen/splash.png)

###### Splash Screen pantalla de bienvenida del aplicativo

1. Carga de datos
   -Username
   -etc

![Menu Screen](/screen/menu.png)

###### Menu Screen pantalla de con las opciones disponible del aplicativo

1. Ventas
   - Detalle de ventas
2. Productos
   - Lista de productos
3. Inventario
   - Productos en inventario
4. Finanzas
   - Detalle de ventas realizadas

## Tareas

- [x] Navegacion entre pantallas
- [x] Splash Screen
- [x] Menu Screen
- [ ] Settings Screen
- [x] Implementar el DataStore
- [x] Products Screen
- [x] Implementar el Hilt
- [x] Implementar el RoomDataBase
- [ ] Agregar Productos
- [x] Listar Productos
- [ ] Eliminar Productos
- [ ] Editar Productos
- [ ] Editar username en la settings screen