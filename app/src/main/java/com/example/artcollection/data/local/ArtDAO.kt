package com.example.artcollection.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artcollection.data.model.Art

@Dao
interface ArtDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art : Art)

    @Delete
    suspend fun deleteArt(art: Art)

    //livedata zaten asenkron calıstıgı için suspen yazmamıza gerek yok
    @Query("SELECT * FROM arts")
    fun observeArts(): LiveData<List<Art>>

}