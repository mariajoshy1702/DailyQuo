package com.example.dailyquo.ui.theme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dailyquo.R
import com.example.dailyquo.data.room.Quote
import com.example.dailyquo.ui.theme.QuoteViewModel

@Composable
fun ShowSaveIconButton(quote: Quote, viewModel: QuoteViewModel, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val savedQuotes by viewModel.savedQuotes.collectAsState()
    val isSaved = savedQuotes.any { it.text == quote.text }

    Box(
        modifier = modifier.size(40.dp)
    ) {
        IconButton(
            onClick = {
                val toastMessage: String
                if (isSaved) {
                    viewModel.deleteQuoteByText(quote)
                    toastMessage = context.getString(R.string.removed_from_saved_quotes)
                } else {
                    viewModel.addQuote(quote)
                    toastMessage = context.getString(R.string.added_to_saved_quotes)
                }
                showToastMessage(context, toastMessage)
            },
        ) {
            Icon(
                imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                contentDescription = if (isSaved) "Saved" else "Removed",
                modifier = Modifier
                    .wrapContentHeight()
                    .size(40.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}


@Composable
fun ShowDeleteIconButton(onDeleteClick: () -> Unit){
    IconButton(
        onClick = {
            onDeleteClick()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete_button),
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun ShowCopyIconButton(onCopyClick: () -> Unit){
    IconButton(
        onClick = {
            onCopyClick()
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.ContentCopy,
            contentDescription = stringResource(R.string.copy),
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}