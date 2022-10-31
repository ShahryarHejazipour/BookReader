package com.tispunshahryar960103.bookreader.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tispunshahryar960103.bookreader.data.Resource
import com.tispunshahryar960103.bookreader.model.Item
import com.tispunshahryar960103.bookreader.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: BookRepository):ViewModel() {


    suspend fun getBookInfo(bookId:String):Resource<Item>{

      return repository.getBookInfo(bookId)

    }





}