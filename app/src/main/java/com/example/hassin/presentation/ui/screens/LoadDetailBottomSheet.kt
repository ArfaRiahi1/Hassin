package com.example.hassin.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hassin.domain.model.Load

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadDetailBottomSheet(
    load: Load,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxWidth(),
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "جزئیات بار",
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "بستن"
                    )
                }
            }

            // ردیف‌ها
            Column(modifier = Modifier.fillMaxWidth()) {
                DetailRow(label = "مبدا:", value = load.originCity)
                DetailRow(label = "مقصد:", value = load.destinationCity)
                DetailRow(label = "وزن:", value = "${load.weightInTon} تن")
                DetailRow(label = "بار:", value = load.itemName)
                DetailRow(label = "بسته‌بندی:", value = load.packaging)
                DetailRow(label = "تاریخ بارگیری:", value = load.loadingDate)
            }

            // دکمه تأیید
            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6A00),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "با ${load.priceInMillionToman} میلیون تومان کرایه می‌برم")
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoadDetailBottomSheet() {
    val sampleLoad = Load(
        id = 1,
        originCity = "تهران",
        originProvince = "تهران",
        destinationCity = "کرمان",
        destinationProvince = "کرمان",
        weightInTon = 12,
        priceInMillionToman = 2.0,
        packaging = "کونی",
        loadingDate = "۲۰ شهریور",
        itemName = "سیمان"
    )

    LoadDetailBottomSheet(
        load = sampleLoad,
        onConfirm = {},
        onDismiss = {}
    )
}
