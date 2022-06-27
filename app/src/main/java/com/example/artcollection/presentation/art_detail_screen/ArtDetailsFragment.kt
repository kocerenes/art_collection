package com.example.artcollection.presentation.art_detail_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artcollection.R
import com.example.artcollection.databinding.FragmentArtDetailsBinding
import com.example.artcollection.utils.Status
import com.example.artcollection.utils.selectedImageUrl
import javax.inject.Inject

class ArtDetailsFragment @Inject constructor(
    private val glide : RequestManager
) : Fragment(R.layout.fragment_art_details) {

    private var _binding : FragmentArtDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel : ArtDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ArtDetailViewModel::class.java]

        subscribeToObserver()

        binding.artImageView.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImagesFragment())
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.btnSave.setOnClickListener {
            viewModel.makeArt(binding.tvArtName.text.toString(),binding.tvArtistName.text.toString(),binding.tvYear.text.toString())
        }

    }

    private fun subscribeToObserver(){
        selectedImageUrl.observe(viewLifecycleOwner, Observer { url ->
            binding?.let {
                glide.load(url).into(it.artImageView)
            }
        })

        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertArtMsg()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error",Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {

                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}