package com.markusw.timetonictest.core.presentation

sealed class Screens(
    val route: String
) {

    data object Login: Screens("login")

    data object Landing: Screens("landing")

}