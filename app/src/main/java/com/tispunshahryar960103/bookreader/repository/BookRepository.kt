package com.tispunshahryar960103.bookreader.repository

import com.tispunshahryar960103.bookreader.data.DataOrException
import com.tispunshahryar960103.bookreader.model.Book
import com.tispunshahryar960103.bookreader.model.Item
import com.tispunshahryar960103.bookreader.network.BooksAPI
import retrofit2.http.Query
import javax.inject.Inject

class BookRepository @Inject constructor(private val api : BooksAPI) {


    //Using Wrapper class for adding metadata into the response --- ArrayList<QuestionItem>() ---
   private val dataOrException = DataOrException<List<Item>,Boolean,Exception>()
   private val bookInfoDataOrException = DataOrException<Item,Boolean,Exception>()

    suspend fun getBooks(searchQuery:String) : DataOrException<List<Item>,Boolean,Exception>{

        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllBooks(searchQuery).items
            if (dataOrException.data!!.isNotEmpty()) dataOrException.loading=false


        }catch (e:Exception){
            dataOrException.e = e
        }

        return dataOrException

    }

    suspend fun getBookInfo(bookId:String):DataOrException<Item,Boolean,Exception>{

        try {
            bookInfoDataOrException.loading=true
            bookInfoDataOrException.data=api.getBookInfo(bookId = bookId)
            if (bookInfoDataOrException.data.toString().isNotEmpty()) bookInfoDataOrException.loading=false

        }catch (e:Exception){
            dataOrException.e=e
        }

        return bookInfoDataOrException

    }

}