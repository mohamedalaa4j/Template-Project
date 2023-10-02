package com.am.template.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.template.data.local.models.ReportModel
import com.am.template.domain.repository.Repository
import com.am.template.domain.usecase.GetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {

    private val _dateState = MutableStateFlow<List<ReportModel>>(emptyList())
    val dataState: StateFlow<List<ReportModel>> = _dateState

    private var getDataJob: Job? = null

    private fun getDate() {
        getDataJob?.cancel()
        getDataJob = viewModelScope.launch {
            withContext(coroutineContext) {
                getDataUseCase().collect { result ->
                    _dateState.value = result
                }
            }
        }
    }

    init {
        getDate()
    }
}