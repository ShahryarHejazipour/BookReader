package com.tispunshahryar960103.bookreader.data

import com.tispunshahryar960103.bookreader.model.Book

data class DataOrException<T,Boolean,E:Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null,
)
