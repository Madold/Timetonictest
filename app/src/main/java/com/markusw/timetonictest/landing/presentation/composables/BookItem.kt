package com.markusw.timetonictest.landing.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.markusw.timetonictest.landing.domain.model.Book

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(16.dp)
    ) {

    }
}