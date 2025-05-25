package com.example.hassin.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hassin.domain.model.Load
import com.example.hassin.domain.usecase.GetLoadsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadViewModel @Inject constructor(
    @ApplicationContext internal val context: Context,
    private val getLoadsUseCase: GetLoadsUseCase
) : ViewModel() {
    var loads by mutableStateOf<List<Load>>(emptyList())
        private set

    var selectedLoad by mutableStateOf<Load?>(null)
        private set

    var assignedLoad by mutableStateOf<Load?>(null)
        private set


    private var currentPage = 0

    fun loadMore() {
        viewModelScope.launch {
            val newLoads = getLoadsUseCase(currentPage)
            loads = loads + newLoads
            currentPage++
        }
    }

    fun selectLoad(load: Load) {
        selectedLoad = load
    }

    fun clearSelection() {
        selectedLoad = null
    }

    fun tryAssignLoad(
        load: Load,
        onAlreadyAssigned: (Load) -> Unit,
        onAssigned: () -> Unit
    ) {
        if (assignedLoad != null) {
            onAlreadyAssigned(assignedLoad!!)
        } else {
            assignedLoad = load
            onAssigned()
        }
    }

    fun unassignLoad() {
        assignedLoad = null
    }
}

