package com.markusw.timetonictest.landing.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.markusw.timetonictest.landing.presentation.composables.BookItem

@Composable
fun LandingScreen(
    state: LandingState,
    onEvent: (LandingEvent) -> Unit,
    mainNavController: NavController,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(state.books) { book ->
                BookItem(book = book)
            }
        }
    }

}