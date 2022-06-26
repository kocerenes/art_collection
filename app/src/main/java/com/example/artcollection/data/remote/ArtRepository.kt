package com.example.artcollection.data.remote

import androidx.lifecycle.LiveData
import com.example.artcollection.data.local.model.Art
import com.example.artcollection.data.remote.model.ImageResponse
import com.example.artcollection.utils.Resource

interface ArtRepository {

    suspend fun insertArt(art:Art)

    suspend fun deleteArt(art: Art)
    
    fun getArt(): LiveData<List<Art>>

    suspend fun searchImage(imageString: String): Resource<ImageResponse>

}