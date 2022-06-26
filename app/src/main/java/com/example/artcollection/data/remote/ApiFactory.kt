package com.example.artcollection.data.remote

import com.example.artcollection.data.remote.model.ImageResponse
import com.example.artcollection.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFactory {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery : String,
        @Query("key") apiKey : String = API_KEY
    ): Response<ImageResponse>

}