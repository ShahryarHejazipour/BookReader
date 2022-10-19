package com.tispunshahryar960103.bookreader.screens.login

data class LoginState(val status: Status,val message:String?=null){

    companion object{

        val SUCCESS = LoginState(Status.SUCCESS)
        val FAILED = LoginState(Status.FAILED)
        val LOADING = LoginState(Status.LOADING)
        val IDLE = LoginState(Status.IDLE)

    }

}
