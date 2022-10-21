package com.tispunshahryar960103.bookreader.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tispunshahryar960103.bookreader.R
import com.tispunshahryar960103.bookreader.components.EmailInput
import com.tispunshahryar960103.bookreader.components.PasswordInput
import com.tispunshahryar960103.bookreader.components.ReaderLogo
import com.tispunshahryar960103.bookreader.navigation.ReaderScreens

@Composable
fun BookReaderLoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {

    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }
    
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            ReaderLogo()

            if (showLoginForm.value) UserForm(){email,password ->
                //todo : FB Login
                viewModel.signInWithEmailAndPassword(email = email, password = password){
                    navController.navigate(ReaderScreens.HomeScreen.name)
                }
            } else {
                UserForm(isCreateAccount = true) {email,password ->
                    //todo : create FB User account
                    viewModel.createUserWithEmailAndPassword(email = email, password = password){
                        navController.navigate(ReaderScreens.HomeScreen.name)
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            
            Row(modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {

                Text(text = "New User?")
                Text(
                    text = if (showLoginForm.value) "Sign Up" else "Login",
                    modifier = Modifier
                        .padding(5.dp)
                        .clickable(
                            onClick = { showLoginForm.value = !showLoginForm.value }),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondaryVariant
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isCreateAccount: Boolean = false,
    loading: Boolean = false,
    onDone: (String, String) -> Unit = { email, password -> },
) {

    val email = rememberSaveable{ mutableStateOf("")}
    val password = rememberSaveable{ mutableStateOf("")}
    val valid = remember(email.value,password.value) { email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty() }
    val passwordVisibility = rememberSaveable { mutableStateOf(false)}

    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    
    Column(modifier = Modifier
        .height(250.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        
        if (isCreateAccount) Text(text = stringResource(id = R.string.create_account),
            modifier = Modifier.padding(4.dp)) else Text(text = "")
        EmailInput(
            emailState = email,
            enabled = !loading,
            keyboardActions = KeyboardActions { passwordFocusRequest.requestFocus() }
        )
        
        PasswordInput(
            passwordState = password,
            passwordVisibility = passwordVisibility,
            enabled = !loading,//todo : change this
            modifier = Modifier.focusRequester(passwordFocusRequest),
            keyboardActions = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
            }
        )
        SubmitButton(
            textId = if (isCreateAccount) "Create Account" else "Login",
            loading = loading,
            validInput = valid
        ){
            onDone(email.value.trim(),password.value.trim())
            keyboardController?.hide()
        }

    }
}

@Composable
fun SubmitButton(
    textId: String,
    loading: Boolean,
    validInput: Boolean,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = !loading && validInput
    ) {
        if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp))

    }

}