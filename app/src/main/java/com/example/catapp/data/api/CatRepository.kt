package com.example.catapp.data.api

import kotlinx.coroutines.flow.Flow

interface CatRepository<T, S> {
    suspend fun getCatImages(): List<T>
    suspend fun getCatDetails(id: String): S
}