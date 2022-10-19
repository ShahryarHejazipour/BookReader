package com.tispunshahryar960103.bookreader.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel:ViewModel() {
    /*
   this is for connecting to the Google Firebase Authentication :
    https://console.firebase.google.com/u/0/project/areader-9040d/authentication/users
    */
    private val auth : FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false) // internal variable
    val loading : LiveData<Boolean> = _loading // public variable

    fun signInWithEmailAndPassword(email:String, password:String,home : () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful) {
                        Log.d("FB", "signupWithEmailAndPassword: Yayyaya!! ${task.result.toString()}")
                       // TODO("take them to Home")
                        home()
                    }else{
                        Log.d("FB", "signupWithEmailAndPassword: ${task.result.toString()}")
                    }
                }

        }catch (ex:Exception){
            Log.d("FB", "signupWithEmailAndPassword: ${ex.message}")
        }
    }

    fun createUserWithEmailAndPassword(email:String,password:String,home: () -> Unit){

        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    home()
                }else{
                    Log.d("FB", "createUserWithEmailAndPassword: ${task.result.toString()}")
                }
                _loading.value = false

            }
        }


    }
}