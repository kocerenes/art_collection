package com.presentation.arts_screen

import com.data.repository.FakeArtRepository
import com.example.artcollection.presentation.arts_screen.ArtsViewModel
import org.junit.Before

class ArtsViewModelTest {

    private lateinit var viewModel : ArtsViewModel

    @Before
    fun setup(){
        viewModel = ArtsViewModel(FakeArtRepository())
    }

}