package com.tispunshahryar960103.bookreader.model

data class MUser(
    val id: String?,
    val userId: String,
    val displayName: String,
    val avatarUrl: String,
    val quote: String,
    val profession: String,
){

    //this function is to convert MUser Object into the Map or HashMap
    fun toMap(): MutableMap<String,Any>{

        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "quote" to this.quote,
            "avatarUrl" to this.avatarUrl,
            "profession" to this.profession
        )

    }
}
