package com.tispunshahryar960103.bookreader.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tispunshahryar960103.bookreader.data.Resource
import com.tispunshahryar960103.bookreader.model.Item
import com.tispunshahryar960103.bookreader.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {

    var list : List<Item> by mutableStateOf(listOf())

    init {
        loadBooks()
    }

    private fun loadBooks() {
        searchBooks("flutter")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) return@launch
            try {
                when(val response = repository.getBooks(query)){
                    is Resource.Success ->{
                        list = response.data!!
                    }
                    
                    is Resource.Error -> {
                        Log.d("Network", "searchBooks: Failed getting Books!")
                    }

                    else -> {}
                }

            }catch (exception:Exception){
                Log.d("Network", "searchBooks: ${exception.message.toString()}")
            }
        }

    }


}