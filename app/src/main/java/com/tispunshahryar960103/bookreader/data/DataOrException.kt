package com.tispunshahryar960103.bookreader.data

// This Is a ---- Wrapper class ---- for adding Metadata into the response data
data class DataOrException<T,Boolean,E:Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null,
)
