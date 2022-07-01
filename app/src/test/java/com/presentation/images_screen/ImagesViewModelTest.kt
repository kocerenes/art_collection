package com.presentation.images_screen

import com.data.repository.FakeArtRepository
import com.example.artcollection.presentation.images_screen.ImagesViewModel
import org.junit.Before

class ImagesViewModelTest {

    private lateinit var viewModel: ImagesViewModel

    @Before
    fun setup(){
        viewModel = ImagesViewModel(FakeArtRepository())
    }

}