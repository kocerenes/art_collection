package com.example.artcollection.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData



private val selectedImage = MutableLiveData<String>()
val selectedImageUrl : LiveData<String>
    get() = selectedImage

fun setSelectedImage(url: String){
    selectedImage.postValue(url)
}
