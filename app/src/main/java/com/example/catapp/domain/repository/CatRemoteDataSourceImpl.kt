package com.example.catapp.domain.repository

import com.example.catapp.data.api.CatAPI
import com.example.catapp.data.api.CatRepository
import com.example.catapp.data.api.model.CatImage
import com.example.catapp.data.api.model.CatImageDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CatRemoteDataSourceImpl(private val catApiService: CatAPI,
                              private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : CatRepository<CatImage, CatImageDetails> {
    override fun getCatImages(): Flow<List<CatImage>> {
        return flow {
            emit(catApiService.getCatImages())
        }.flowOn(dispatcher)
    }

    override fun getCatDetails(id: String): Flow<CatImageDetails> {
        return flow {
            emit(catApiService.getCatDetails(id))
        }.flowOn(dispatcher)
    }
}