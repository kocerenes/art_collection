package com.example.artcollection.presentation.images_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.artcollection.R
import javax.inject.Inject

class ImagesFragment @Inject constructor(
    private val imageRecyclerAdapter: ImageRecyclerAdapter
) : Fragment(R.layout.fragment_images) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}