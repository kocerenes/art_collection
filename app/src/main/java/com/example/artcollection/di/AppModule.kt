package com.example.artcollection.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.artcollection.R
import com.example.artcollection.data.local.ArtDAO
import com.example.artcollection.data.local.ArtDatabase
import com.example.artcollection.data.remote.ApiFactory
import com.example.artcollection.data.repository.ArtRepository
import com.example.artcollection.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,ArtDatabase::class.java,"ArtCollectionDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(
        database: ArtDatabase
    ) = database.artDao()

    @Singleton
    @Provides
    fun provideConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun injectRetrofitAPI(
        gsonConverterFactory: GsonConverterFactory
    ) : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiFactory(
        retrofit: Retrofit
    ): ApiFactory{
        return retrofit.create(ApiFactory::class.java)
    }

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )

    @Singleton
    @Provides
    fun provideNormalRepo(dao: ArtDAO,api: ApiFactory) = ArtRepository(dao,api) as com.example.artcollection.data.remote.ArtRepository



}