package com.am.template.presentation.viewmodel.navbottom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

//    private val _homeApisState = MutableStateFlow<UiState<HomeApisResponse>>(UiState.Empty())
//    val homeApisState: StateFlow<UiState<HomeApisResponse>> get() = _homeApisState
//
//    private val _addFavouriteState = MutableSharedFlow<UiState<ResultModel>>()
//    val addFavouriteState: SharedFlow<UiState<ResultModel>> = _addFavouriteState

    private var homeApisJob: Job? = null


    fun cancelRequest() {
        homeApisJob?.cancel()

    }

    fun getHomeApis() {
        homeApisJob?.cancel()
        homeApisJob = viewModelScope.launch {
            delay(Constants.ANIMATION_DELAY)
            withContext(coroutineContext) {
//                homeApisUseCase().collect { result ->
//                    when (result) {
//                        is Resource.Success -> {
//                            result.data?.let { data ->
//                                _homeApisState.value = UiState.Success(data)
////                                _homeApisState.emit(UiState.Success(data))
//                            } ?: run { _homeApisState.value = UiState.Empty() }
////                            } ?: run { _homeApisState.emit(UiState.Empty()) }
//                        }
//
//                        is Resource.Loading -> {
//                            _homeApisState.value = (UiState.Loading())
////                            _homeApisState.emit( UiState.Loading())
//                        }
//
//                        is Resource.Error -> {
//                            _homeApisState.value = UiState.Error(result.message!!)
////                            _homeApisState.emit( UiState.Error(result.message!!))
//                        }
//
//                        else -> Unit
//                    }
//                }
            }
        }
    }

}