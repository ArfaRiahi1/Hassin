package com.example.hassin.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hassin.presentation.components.LoadCard
import com.example.hassin.presentation.viewmodel.LoadListViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadListWithBottomSheet(
    viewModel: LoadListViewModel = viewModel()
) {
    val loads by viewModel.loads.collectAsState()
    val selectedLoadId by viewModel.selectedLoadId.collectAsState()

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            selectedLoadId?.let { loadId ->
                val load = loads.find { it.id == loadId }
                if (load != null) {
                    LoadDetailBottomSheetContent(
                        load = load,
                        onAddBoxClick = {
                            // عملیات اضافه کردن کادر
                        },
                        onClose = {
                            coroutineScope.launch {
                                bottomSheetState.hide()
                                viewModel.clearSelection()
                            }
                        }
                    )
                }
            } ?: Box(Modifier.height(1.dp)) {} // محتوای خالی زمانی که چیزی انتخاب نشده
        }
    ) {
        LazyColumn {
            items(loads.size) { index ->
                val enabled = viewModel.isLoadSelectable(loads[index].id)
                LoadCard(
                    load = loads[index],
                    enabled = enabled,
                    onClick = {
                        if (enabled) {
                            viewModel.selectLoad(loads[index].id)
                            coroutineScope.launch {
                                bottomSheetState.show()
                            }
                        }
                    }
                )
            }
        }
    }
}