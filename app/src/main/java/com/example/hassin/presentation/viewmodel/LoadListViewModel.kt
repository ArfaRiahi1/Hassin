package com.example.hassin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hassin.domain.model.Load
import com.example.hassin.domain.usecase.GetLoadsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadListViewModel @Inject constructor(
    private val getLoadsUseCase: GetLoadsUseCase
) : ViewModel() {

    private val _loads = MutableStateFlow<List<Load>>(emptyList())
    val loads: StateFlow<List<Load>> = _loads

    private val _selectedLoadId = MutableStateFlow<String?>(null)
    val selectedLoadId: StateFlow<String?> = _selectedLoadId

    fun loadLoads() {
        viewModelScope.launch {
            val loaded = getLoadsUseCase()
            _loads.value = loaded
        }
    }

    fun selectLoad(id: String) {
        if (_selectedLoadId.value == null) {
            _selectedLoadId.value = id
        }
    }

    fun clearSelection() {
        _selectedLoadId.value = null
    }

    // تابع کمکی برای چک کردن اینکه بار غیرقابل انتخاب هست یا نه
    fun isLoadSelectable(loadId: String): Boolean {
        return _selectedLoadId.value == null || _selectedLoadId.value == loadId
    }
}