package com.markusw.timetonictest.core.presentation

/**
 * Sealed class that represents the screens of the app.
 * Each screen is represented by an object that contains the route of the screen.
 * This way, the route is strongly typed and can be accessed from anywhere in the app.
 */
sealed class Screens(
    val route: String
) {

    data object Login: Screens("login")

    data object Landing: Screens("landing")

}