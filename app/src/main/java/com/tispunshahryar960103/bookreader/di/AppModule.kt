package com.tispunshahryar960103.bookreader.di

import com.google.firebase.firestore.FirebaseFirestore
import com.tispunshahryar960103.bookreader.network.BooksAPI
import com.tispunshahryar960103.bookreader.repository.FireRepository
import com.tispunshahryar960103.bookreader.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBooksAPI():BooksAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideFireBookRepository() =
        FireRepository(queryBook = FirebaseFirestore.getInstance().collection("books"))
}