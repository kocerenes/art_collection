package com.example.artcollection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.artcollection.data.model.Art

@Database(entities = [Art::class], version = 1)
abstract class ArtDatabase: RoomDatabase() {

    abstract fun artDao() : ArtDAO

}