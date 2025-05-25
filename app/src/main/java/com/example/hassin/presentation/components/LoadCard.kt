package com.example.hassin.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hassin.R
import com.example.hassin.domain.model.Load

@Composable
fun LoadCard(
    load: Load,
    enabled: Boolean,
    onClick: () -> Unit,
    isLocked: Boolean,
    ) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth()
            .clickable(enabled = enabled, onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row(modifier = Modifier.fillMaxWidth()) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_circle),
                        contentDescription = "مبدا",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    VerticalDivider(
                        modifier = Modifier
                            .height(24.dp)
                            .width(1.dp),
                        color = Color.LightGray
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.ic_square),
                        contentDescription = "مقصد",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Black
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(load.originCity)
                            }
                            append(" (استان ${load.originProvince})")
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(load.destinationCity)
                            }
                            append(" (استان ${load.destinationProvince})")
                        }
                    )
                }

                if (isLocked) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "locked",
                        tint = Color(0xFFFF6A00),
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                            .size(18.dp)
                    )
                }
            }


            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Color.LightGray
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_weight),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = "${load.weightInTon} تن",
                        modifier = Modifier.padding(start = 4.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "${load.priceInMillionToman} میلیون تومان",
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoadCard() {
    val sampleLoad = Load(
        id = 1,
        originCity = "تهران",
        originProvince = "تهران",
        destinationCity = "کرمان",
        destinationProvince = "کرمان",
        weightInTon = 12,
        priceInMillionToman = 2.5,
        packaging = "کونی",
        loadingDate = "۲۰ شهریور",
        itemName = "سیمان"
    )

    LoadCard(
        load = sampleLoad,
        enabled = true,
        onClick = {},
        true
    )
}