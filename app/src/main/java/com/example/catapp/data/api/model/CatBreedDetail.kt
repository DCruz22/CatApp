package com.example.catapp.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatBreedDetail(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "temperament") val temperament: String,
    @field:Json(name = "origin") val origin: String,
    @field:Json(name = "life_span") val lifeSpan: String,
    @field:Json(name = "wikipedia_url") val wikipediaUrl: String
)