package com.example.hassin.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hassin.R
import com.example.hassin.domain.model.Load

@Composable
fun LoadCard(
    load: Load,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(enabled = enabled, onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ) {
        Row {
            Column {
                Icon(
                    painter = painterResource(id = R.drawable.ic_circle),
                    contentDescription = "Check Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
                VerticalDivider()
                Icon(
                    painter = painterResource(id = R.drawable.ic_square),
                    contentDescription = "Check Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
            }
            Column {
                Row {
                    Text(text = load.origin.first)
                    Text(text = "(استان ${load.origin.second})")
                }
                Row {
                    Text(text = load.destination.first)
                    Text(text = "(استان ${load.destination.second})")
                }
            }
            HorizontalDivider()
            Row {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_square),
                        contentDescription = "Check Icon",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                    Text(text = "${load.weight} تن")
                }
                Text(text = "${load.price} میلیون تومان")
            }
        }
    }
}