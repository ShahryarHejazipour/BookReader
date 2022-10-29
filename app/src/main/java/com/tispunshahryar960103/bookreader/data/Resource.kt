package com.tispunshahryar960103.bookreader.data

/*
This Is an another wrapper class for Using in the Repository classes, Like DataOrException Class,
for managing the response data
 */
sealed class Resource<T>(val data: T? = null, val message: String? =null){
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String?, data: T? = null): Resource<T>(data, message )
    class Loading<T>(data: T? = null): Resource<T>(data)
}
