package com.example.artcollection.factory


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.artcollection.presentation.art_detail_screen.ArtDetailsFragment
import com.example.artcollection.presentation.arts_screen.ArtRecyclerAdapter
import com.example.artcollection.presentation.arts_screen.ArtsFragment
import com.example.artcollection.presentation.images_screen.ImageRecyclerAdapter
import com.example.artcollection.presentation.images_screen.ImagesFragment
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter,
    private val glide : RequestManager,
    private val imageRecyclerAdapter: ImageRecyclerAdapter
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ArtsFragment::class.java.name -> ArtsFragment(artRecyclerAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            ImagesFragment::class.java.name -> ImagesFragment(imageRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }

}