package com.example.catapp.data.api

import kotlinx.coroutines.flow.Flow

interface CatRepository<T, S> {
    fun getCatImages(): Flow<List<T>>
    fun getCatDetails(id: String): Flow<S>
}