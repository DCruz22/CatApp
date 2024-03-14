package com.example.catapp.presentation.catdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.api.model.CatBreedDetail
import com.example.catapp.data.api.model.CatImage
import com.example.catapp.data.api.model.CatImageDetails
import com.example.catapp.domain.usecase.GetCatDetailsUseCase
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

class CatDetailsViewModel(
    private val useCase: GetCatDetailsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _catDetails = MutableStateFlow<CatImageDetails?>(null)
    val catDetails: StateFlow<CatImageDetails?> = _catDetails

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    fun getCatImages(id: String) {
        useCase.getCatDetailsList(id)
            .onStart {
                _isLoading.value = true
            }
            .onCompletion {
                _isLoading.value = false
            }
            .onEach {
                _catDetails.value = it
            }
            .catch {
                _isError.value = true
            }
            .flowOn(dispatcher)
            .launchIn(viewModelScope)
    }
}