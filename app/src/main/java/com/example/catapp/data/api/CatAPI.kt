package com.example.catapp.data.api

import com.example.catapp.data.api.model.CatImage
import com.example.catapp.data.api.model.CatImageDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface CatAPI {

    @GET("images/search?limit=$LIMIT")
    suspend fun getCatImages(): List<CatImage>

    @GET("images/search/:id")
    suspend fun getCatDetails(@Path("id") id: String): CatImageDetails

    companion object {
        const val LIMIT = 20
    }
}