package com.markusw.timetonictest.auth.presentation

data class LoginState(
    val email: String = "android.developer@timetonic.com",
    val password: String = "Android.developer1",
    val isLoading: Boolean = false,
)
