package com.example.hassin.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hassin.domain.model.Load

@Composable
fun LoadDetailBottomSheetContent(
    load: Load,
    onAddBoxClick: () -> Unit,
    onClose: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "جزئیات بار", style = MaterialTheme.typography.bodyMedium)
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "بستن")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(text = "مبدا:")
            Text(text = load.origin.first)
        }
        Row {
            Text(text = "مقصد:")
            Text(text = load.destination.first)
        }
        Row {
            Text(text = "وزن:")
            Text(text = " تن${load.weight}")
        }
        Row {
            Text(text = "مبدا:")
            Text(text = "${load.origin}")
        }
        Row {
            Text(text = "بار:")
            Text(text = load.kind)
        }
        Row {
            Text(text = "بسته بندی:")
            Text(text = load.packing)
        }
        Row {
            Text(text = "تاریخ بارگیری:")
            Text(text = load.loadingDate)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAddBoxClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("با ${load.price} میلیون تومان کرایه می‌برم")
        }
    }
}