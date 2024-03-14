package com.example.catapp.domain.usecase

import com.example.catapp.data.api.CatRepository
import com.example.catapp.data.api.model.CatImage
import com.example.catapp.data.api.model.CatImageDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class GetCatDetailsUseCase(
    private val remoteDataSource: CatRepository<CatImage, CatImageDetails>,
) {
    fun getCatDetailsList(id: String) = flow {
        emit(remoteDataSource.getCatDetails(id))
    }.flowOn(Dispatchers.IO)
}