package com.example.artcollection.presentation.images_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.RequestManager
import com.example.artcollection.R
import com.example.artcollection.data.local.model.Art
import javax.inject.Inject

class ImageRecyclerAdapter @Inject constructor(
    val glide: RequestManager
): RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder>() {

    class ImageViewHolder(view : View) : RecyclerView.ViewHolder(view)

    private var onItemClickListener : ((String) -> Unit) ?= null

    private val diffUtil = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val diffList = AsyncListDiffer(this,diffUtil)

    var images : List<String>
        get() = diffList.currentList
        set(value) = diffList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.images_recycler_row,parent,false)
        return ImageViewHolder(view)
    }

    fun setOnItemClickListener(listener : (String) -> Unit){
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.ivSingle)
        val url = images[position]
        holder.itemView.apply {
            glide.load(url).into(imageView)

            setOnItemClickListener {
                onItemClickListener?.let {
                    it(url)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}