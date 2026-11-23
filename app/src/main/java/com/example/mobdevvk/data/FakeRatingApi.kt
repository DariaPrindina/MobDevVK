package com.example.mobdevvk.data

import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeRatingApi @Inject constructor() : RatingApi {

    override suspend fun getRating(id: String): Float {
        delay(1500)
        return (30..50).random() / 10f
    }
}
