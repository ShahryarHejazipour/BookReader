package com.tispunshahryar960103.bookreader.network

import com.tispunshahryar960103.bookreader.model.Book
import com.tispunshahryar960103.bookreader.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BooksAPI {

    @GET("volumes")
    suspend fun getAllBooks(@Query("q") query: String) : Book

    @GET("volumes/{bookId}")
    suspend fun getBookInfo(@Path("bookId") bookId : String) : Item
}