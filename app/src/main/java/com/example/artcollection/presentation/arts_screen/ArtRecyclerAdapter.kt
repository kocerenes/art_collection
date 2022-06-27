package com.example.artcollection.presentation.arts_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artcollection.R
import com.example.artcollection.data.local.model.Art
import javax.inject.Inject

class ArtRecyclerAdapter @Inject constructor(
    private val glide : RequestManager
): RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {

    class ArtViewHolder(view : View) : RecyclerView.ViewHolder(view)

    private val diffUtil = object : DiffUtil.ItemCallback<Art>(){
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }
    }

    private val diffList = AsyncListDiffer(this,diffUtil)

    var arts : List<Art>
        get() = diffList.currentList
        set(value) = diffList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_recycler_row,parent,false)
        return ArtViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.ivArtInRow)
        val nameText = holder.itemView.findViewById<TextView>(R.id.tvArtNameInRow)
        val artistNameText = holder.itemView.findViewById<TextView>(R.id.tvArtistNameInRow)
        val yearText = holder.itemView.findViewById<TextView>(R.id.tvArtYearInRow)
        val art = arts[position]
        holder.itemView.apply {
            nameText.text = art.name
            artistNameText.text = art.artistName
            yearText.text = art.year.toString()
            glide.load(art.imageUrl).into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return arts.size
    }

}