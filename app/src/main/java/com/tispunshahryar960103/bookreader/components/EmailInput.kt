package com.tispunshahryar960103.bookreader.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    label: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType = KeyboardType.Email
) {

    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = label,
        enabled = enabled,
        imeAction = imeAction,
        keyboardType = keyboardType,
        keyboardActions = keyboardActions,
        isSingleLined = true
    )
}