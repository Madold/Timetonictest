package com.markusw.timetonictest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.markusw.timetonictest.auth.presentation.LoginScreen
import com.markusw.timetonictest.auth.presentation.LoginViewModel
import com.markusw.timetonictest.auth.presentation.LoginViewModelEvent
import com.markusw.timetonictest.core.domain.local.LocalDataStore
import com.markusw.timetonictest.core.presentation.Screens
import com.markusw.timetonictest.core.utils.Constants
import com.markusw.timetonictest.landing.presentation.LandingScreen
import com.markusw.timetonictest.landing.presentation.LandingViewModel
import com.markusw.timetonictest.landing.presentation.LandingViewModelEvent
import com.markusw.timetonictest.ui.theme.TimetonictestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localDataStore: LocalDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val isUserLoggedIn = localDataStore.getData(Constants.SESSION_KEY, defaultValue = "none") != "none"
                && localDataStore.getData(Constants.O_U, defaultValue = "none") != "none"

        setContent {
            TimetonictestTheme {
                Surface {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = if (isUserLoggedIn) Screens.Landing.route else Screens.Login.route) {
                        composable(Screens.Login.route) {

                            val viewModel = hiltViewModel<LoginViewModel>()
                            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                viewModel.events.collectLatest { event ->
                                    when (event) {
                                        is LoginViewModelEvent.LoginError -> {
                                            Toast.makeText(
                                                this@MainActivity,
                                                event.message.asString(this@MainActivity),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        is LoginViewModelEvent.LoginSuccess -> {
                                            navController.popBackStack()
                                            navController.navigate(Screens.Landing.route)
                                        }
                                    }
                                }
                            }

                            LoginScreen(
                                state = uiState,
                                onEvent = viewModel::onEvent,
                                mainNavController = navController
                            )
                        }

                        composable(Screens.Landing.route) {
                            val viewModel = hiltViewModel<LandingViewModel>()
                            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                viewModel.events.collectLatest { event ->
                                    when (event) {
                                        is LandingViewModelEvent.CloseSessionError -> {
                                            Toast.makeText(
                                                this@MainActivity,
                                                event.message.asString(this@MainActivity),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        is LandingViewModelEvent.CloseSessionSuccess -> {
                                            navController.popBackStack()
                                            navController.navigate(Screens.Login.route)
                                        }
                                    }
                                }
                            }

                            LandingScreen(
                                state = uiState,
                                onEvent = viewModel::onEvent,
                                mainNavController = navController
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimetonictestTheme {
        Greeting("Android")
    }
}