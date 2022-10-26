package com.tispunshahryar960103.bookreader.repository

import com.tispunshahryar960103.bookreader.data.DataOrException
import com.tispunshahryar960103.bookreader.model.Book
import com.tispunshahryar960103.bookreader.model.Item
import com.tispunshahryar960103.bookreader.network.BooksAPI
import retrofit2.http.Query
import javax.inject.Inject

class BookRepository @Inject constructor(private val api : BooksAPI) {


   private val dataOrException = DataOrException<List<Item>,Boolean,Exception>()

   /* suspend fun getBooks(searchQuery:String) : DataOrException<List<Item>,Boolean,Exception>{

    }*/

}