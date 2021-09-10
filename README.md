# MyPocketShop



## Tecnologias

* [Android JetPack](https://developer.android.com/jetpack?hl=es)
* [Compose](https://developer.android.com/jetpack/compose?hl=es)
* [Hilt y Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt-navigation)
* [Compose Material design icons - Core](https://mvnrepository.com/artifact/androidx.compose.material/material-icons-core?repo=google)
* [Compose Material design icons - Extended](https://mvnrepository.com/artifact/androidx.compose.material/material-icons-extended?repo=google)
* [Hilt docs](https://dagger.dev/hilt/)
* [RoomDataBase](https://developer.android.com/jetpack/androidx/releases/room)

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

1. Primer elemento de la lista
   - Primer elemento de la lista anidado
     - Segundo elemento de la lista anidado

## Tareas


- [x] Navegacion entre pantallas
- [x] Splash Screen
- [x] Menu Screen
- [ ] Settings Screen
- [ ] Implementar el DataStore
- [ ] Products Screen
- [ ] Implementar el Hilt
- [ ] Implementar el RoomDataBase
- [ ] Agregar Productos
- [ ] Listar Productos
- [ ] Eliminar Productos
- [ ] Editar Productos