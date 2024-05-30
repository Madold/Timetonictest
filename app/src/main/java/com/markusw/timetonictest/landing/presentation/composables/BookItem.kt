package com.markusw.timetonictest.landing.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.markusw.timetonictest.R
import com.markusw.timetonictest.landing.domain.model.Book

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(32.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.coverUrl)
                .crossfade(true)
                .error(R.drawable.generic_book_cover)
                .build()
            ,
            contentDescription = stringResource(id = R.string.book_cover_description),
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.FillWidth,
        )

        Text(
            text = book.name,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )

    }

    HorizontalDivider()
}