package com.data.local

import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.artcollection.data.local.ArtDAO
import com.example.artcollection.data.local.ArtDatabase
import com.example.artcollection.data.local.model.Art
import com.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class ArtDAOTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ArtDatabase
    private lateinit var dao : ArtDAO

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),ArtDatabase::class.java
        ).allowMainThreadQueries().build()
        dao= database.artDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertArtTesting() = runBlockingTest {
        val exampleArt = Art("Mona Lisa","Da Vinci",1700,"test.com",1)
        dao.insertArt(exampleArt)

        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).contains(exampleArt)
    }

    @Test
    fun deleteArtTesting() = runBlockingTest {
        val exampleArt = Art("Mona Lisa","Da Vinci",1700,"test.com",1)
        dao.insertArt(exampleArt)
        dao.deleteArt(exampleArt)

        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).doesNotContain(exampleArt)
    }


}