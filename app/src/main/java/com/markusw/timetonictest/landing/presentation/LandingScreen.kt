@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.timetonictest.landing.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.markusw.timetonictest.R
import com.markusw.timetonictest.landing.presentation.composables.BookItem

@Composable
fun LandingScreen(
    state: LandingState,
    onEvent: (LandingEvent) -> Unit,
    mainNavController: NavController,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Books") },
                actions = {
                    IconButton(onClick = { onEvent(LandingEvent.CloseSession) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_exit),
                            contentDescription = stringResource(
                                id = R.string.ic_exit_description
                            )
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
            ,
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(state.books) { book ->
                        BookItem(
                            book = book,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                if (state.isClosingSession) {
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
                                Text("Closing session...")
                            }
                        }
                    }
                }

            }
        }
    }

}