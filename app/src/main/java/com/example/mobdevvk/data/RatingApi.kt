package com.example.mobdevvk.data

interface RatingApi {
    suspend fun getRating(id: String): Float
}
