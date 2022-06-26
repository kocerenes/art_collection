package com.example.artcollection.data.remote.model

data class ImageResponse(
    val hits : List<Image>,
    val total: Int,
    val totalHits: Int
)