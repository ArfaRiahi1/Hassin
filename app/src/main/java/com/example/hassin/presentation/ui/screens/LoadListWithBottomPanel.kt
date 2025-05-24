package com.example.hassin.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hassin.presentation.components.LoadCard
import com.example.hassin.presentation.viewmodel.LoadListViewModel

@Composable
fun LoadListWithBottomPanel(
    viewModel: LoadListViewModel = viewModel()
) {
    val loads by viewModel.loads.collectAsState()
    val selectedLoadId by viewModel.selectedLoadId.collectAsState()
    val isPanelVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(loads.size) { index ->
                val enabled = viewModel.isLoadSelectable(loads[index].id)
                LoadCard(
                    load = loads[index],
                    enabled = enabled,
                    onClick = {
                        if (enabled) {
                            viewModel.selectLoad(loads[index].id)
                        }
                    }
                )
            }
        }

        // دکمه پایین صفحه برای نمایش کادر
        if (selectedLoadId != null) {
            Button(
                onClick = { isPanelVisible.value = true },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Text(text = "نمایش بار انتخاب شده")
            }
        }

        // کادر پایین صفحه
        if (isPanelVisible.value && selectedLoadId != null) {
            val selectedLoad = loads.find { it.id == selectedLoadId }
            if (selectedLoad != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .align(Alignment.BottomCenter)
                        .background(Color.LightGray)
                        .padding(16.dp)
                ) {
                    Column {
                        Text(text = "بار انتخاب شده:")
                        Text(text = "${selectedLoad.origin} ➔ ${selectedLoad.destination}")
                        Text(text = "وزن: ${selectedLoad.weight} تن")
                        Text(text = "قیمت: ${selectedLoad.price} میلیون تومان")

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = {
                            isPanelVisible.value = false
                            viewModel.clearSelection()
                        }) {
                            Text(text = "بستن کادر")
                        }
                    }
                }
            }
        }
    }
}