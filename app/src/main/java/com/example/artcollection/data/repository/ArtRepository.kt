package com.example.artcollection.data.repository

import androidx.lifecycle.LiveData
import com.example.artcollection.data.local.ArtDAO
import com.example.artcollection.data.local.model.Art
import com.example.artcollection.data.remote.ApiFactory
import com.example.artcollection.data.remote.ArtRepository
import com.example.artcollection.data.remote.model.ImageResponse
import com.example.artcollection.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDAO: ArtDAO,
    private val apiFactory: ApiFactory
): ArtRepository {
    override suspend fun insertArt(art: Art) {
        artDAO.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDAO.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDAO.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = apiFactory.imageSearch(imageString)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            }else{
                Resource.error("Error",null)
            }
        }catch (e: Exception){
            return Resource.error("No data!",null)
        }
    }
}