package com.example.dailyquo.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquo.R

@Composable
fun QuoteItem(text: String, author: String, onDeleteClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {

            GetLeftCanvas()

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "- $author",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Cursive,
                        )

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
                }
            }
        }
    }
}
