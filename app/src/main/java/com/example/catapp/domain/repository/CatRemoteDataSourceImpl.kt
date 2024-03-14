package com.example.catapp.domain.repository

import com.example.catapp.data.api.CatAPI
import com.example.catapp.data.api.CatRepository
import com.example.catapp.data.api.model.CatImage
import com.example.catapp.data.api.model.CatImageDetails

class CatRemoteDataSourceImpl(private val catApiService: CatAPI) :
    CatRepository<CatImage, CatImageDetails> {
    override suspend fun getCatImages(): List<CatImage> {
        return catApiService.getCatImages()
    }

    override suspend fun getCatDetails(id: String): CatImageDetails {
        return catApiService.getCatDetails(id)
    }
}