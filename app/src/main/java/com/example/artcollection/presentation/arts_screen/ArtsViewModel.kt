package com.example.artcollection.presentation.arts_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artcollection.data.local.model.Art
import com.example.artcollection.data.remote.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel @Inject constructor(
    private val repository: ArtRepository
) : ViewModel() {

    val artList = repository.getArt()

    fun deleteArt(art: Art) = viewModelScope.launch {
        repository.deleteArt(art)
    }

}