package com.markusw.timetonictest.auth.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.markusw.timetonictest.R

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (AuthEvent) -> Unit,
    mainNavController: NavController,
    modifier: Modifier = Modifier,
) {

    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val visualTransformation = if (isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
    val passwordToggleIcon = if (isPasswordVisible) {
        R.drawable.ic_eye
    } else {
        R.drawable.ic_eye_closed
    }

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
            ,
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = state.email,
                    onValueChange = {
                        onEvent(AuthEvent.ChangeEmail(it))
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.emailError != null,
                )

                AnimatedVisibility(visible = state.emailError != null) {
                    state.emailError?.let {
                        Text(
                            text = it.asString(),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

                OutlinedTextField(
                    value = state.password,
                    onValueChange = {
                        onEvent(AuthEvent.ChangePassword(it))
                    },
                    visualTransformation = visualTransformation,
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                painter = painterResource(id = passwordToggleIcon),
                                contentDescription = stringResource(
                                    id = R.string.eye_description
                                )
                            )
                        }
                    },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.passwordError != null,
                )

                AnimatedVisibility(visible = state.passwordError != null) {
                    state.passwordError?.let {
                        Text(
                            text = it.asString(),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

                Button(onClick = { onEvent(AuthEvent.Login) }) {
                    Text("Login")
                }

            }
        }

        if (state.isLoading) {
            Dialog(onDismissRequest = { }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .defaultMinSize(minHeight = 280.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        Text("Authenticating...")
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun LoginScreenPreview(modifier: Modifier = Modifier) {
    LoginScreen(
        state = LoginState(),
        onEvent = {},
        mainNavController = rememberNavController()
    )
}