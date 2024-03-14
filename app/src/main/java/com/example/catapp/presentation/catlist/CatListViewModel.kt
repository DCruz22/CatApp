package com.example.catapp.presentation.catlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.api.model.CatImage
import com.example.catapp.domain.usecase.GetCatImagesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class CatListViewModel(
    private val useCase: GetCatImagesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _catImagesList = MutableStateFlow<CatListUIState>(CatListUIState.Empty)
    val catImagesList: StateFlow<CatListUIState> = _catImagesList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    fun getCatImages() {
        useCase.getCatImageList()
            .onStart {
                _isLoading.value = true
            }
            .onCompletion {
                _isLoading.value = false
            }
            .onEach {
                if (it.isNotEmpty()){
                    _catImagesList.value = CatListUIState.Success(it)
                }
            }
            .catch {
                _isError.value = true
            }
            .flowOn(dispatcher)
            .launchIn(viewModelScope)
    }
}

sealed interface CatListUIState {
    data object Empty : CatListUIState

    data class Success(val catImagesList: List<CatImage>) : CatListUIState
}