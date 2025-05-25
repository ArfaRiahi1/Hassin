package com.example.hassin.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hassin.presentation.components.LoadCard
import com.example.hassin.presentation.viewmodel.LoadViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadListScreen(viewModel: LoadViewModel = hiltViewModel()) {
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    // Load initial data
    LaunchedEffect(Unit) {
        viewModel.loadMore() // This is fine as it's inside LaunchedEffect
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                val message = data.visuals.message
                val actionLabel = data.visuals.actionLabel

                Snackbar(
                    action = {
                        if (actionLabel == "لغو بار") {
                            TextButton(onClick = {
                                viewModel.unassignLoad()
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "بار لغو شد.",
                                        actionLabel = "متوجه شدم",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }) {
                                Text(
                                    text = actionLabel,
                                    color = Color.Red
                                )
                            }
                        } else {
                            // برای سایر پیام‌ها، رنگ پیش‌فرض
                            TextButton(onClick = {
                                scope.launch { data.performAction() }
                            }) {
                                Text(text = actionLabel ?: "", color = Color.Black)
                            }
                        }
                    },
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(text = message)
                }
            }

        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn(state = listState) {
                items(viewModel.loads.size) { index ->
                    val load = viewModel.loads[index]
                    val isLocked =
                        viewModel.assignedLoad != null && viewModel.assignedLoad?.id != load.id

                    LoadCard(
                        load = viewModel.loads[index],
                        enabled = true,
                        isLocked = isLocked,
                        onClick = {
                            viewModel.selectLoad(viewModel.loads[index])

                        }
                    )
                }
            }

            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            LaunchedEffect(lastVisible) {
                if (lastVisible != null && lastVisible >= viewModel.loads.size - 3) {
                    viewModel.loadMore()
                }
            }
            viewModel.selectedLoad?.let { load ->
                LoadDetailBottomSheet(
                    load = load,
                    sheetState = sheetState,
                    onDismiss = {
                        scope.launch {
                            sheetState.hide()
                            viewModel.clearSelection()
                        }
                    },
                    onConfirm = {
                        scope.launch {
                            viewModel.tryAssignLoad(
                                load = load,
                                onAlreadyAssigned = { assigned ->
                                    scope.launch {
                                        sheetState.hide()
                                        viewModel.clearSelection()
                                        snackbarHostState.currentSnackbarData?.dismiss()
                                        snackbarHostState.showSnackbar(
                                            message = "شما قبلاً باری از ${assigned.originCity} ← ${assigned.destinationCity} انتخاب کرده‌اید.",
                                            actionLabel = "لغو بار",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                },
                                onAssigned = {
                                    scope.launch { // Wrap suspend calls in a coroutine scope
                                        sheetState.hide()
                                        viewModel.clearSelection()
                                        snackbarHostState.currentSnackbarData?.dismiss()
                                        snackbarHostState.showSnackbar(
                                            message = "بار با موفقیت تخصیص داده شد.",
                                            actionLabel = "متوجه شدم",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            )
                        }
                    }
                )
            }
        }
    }
}