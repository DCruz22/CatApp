package com.example.catapp.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatImageDetails(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "width") val width: Int,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "breeds") val breeds: List<CatBreedDetail>
)