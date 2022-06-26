package com.example.artcollection.presentation.art_detail_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artcollection.data.local.model.Art
import com.example.artcollection.data.remote.ArtRepository
import com.example.artcollection.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ArtDetailViewModel @Inject constructor(
    private val repository: ArtRepository
): ViewModel() {

    private var insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMessage : LiveData<Resource<Art>>
        get() = insertArtMsg

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
        get() = selectedImage

    public fun setSelectedImage(url: String){
        selectedImage.postValue(url)
    }

    fun resetInsertArtMsg(){
        insertArtMsg = MutableLiveData<Resource<Art>>()
    }

    private fun insertArt(art: Art) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun makeArt(name: String, artistName: String, year: String){
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty()){
            insertArtMsg.postValue(Resource.error("Enter name, artist, year",null))
            return
        }

        val yearInt = try {
            year.toInt()
        }catch (e: Exception){
            insertArtMsg.postValue(Resource.error("Year should be number!!",null))
            return
        }

        // todo =  imageurl selectedImage.value olarak çekilecek
        val art= Art(name,artistName,yearInt,selectedImage.value ?: "")
        insertArt(art)
        setSelectedImage("")
        //setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))

    }

}