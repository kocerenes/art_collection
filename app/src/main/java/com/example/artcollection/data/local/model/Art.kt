package com.example.artcollection.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art(
    @PrimaryKey
    var id : Int? = null,
    var name: String,
    var artistName : String,
    var year : Int,
    var imageUrl : String
)