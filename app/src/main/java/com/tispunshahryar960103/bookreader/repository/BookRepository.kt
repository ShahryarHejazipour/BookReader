package com.tispunshahryar960103.bookreader.repository

import com.tispunshahryar960103.bookreader.data.Resource
import com.tispunshahryar960103.bookreader.model.Item
import com.tispunshahryar960103.bookreader.network.BooksAPI
import javax.inject.Inject

class BookRepository @Inject constructor(private val api : BooksAPI) {

    suspend fun getBooks(searchQuery:String):Resource<List<Item>>{

       return try {
            Resource.Loading(data = true)
            val itemList = api.getAllBooks(searchQuery).items
           Resource.Loading(data = false)
            Resource.Success(data = itemList)
        }catch (exception:Exception){
            Resource.Error(message = exception.message.toString())
        }

    }

    suspend fun getBookInfo(bookId:String):Resource<Item>{
        val response = try {
            Resource.Loading(data = true)
            api.getBookInfo(bookId = bookId)

        }catch (exception:Exception){

          return  Resource.Error("An Error occurred : ${exception.message.toString()}")

        }

        Resource.Loading(false)

        return Resource.Success(data = response)
    }



}