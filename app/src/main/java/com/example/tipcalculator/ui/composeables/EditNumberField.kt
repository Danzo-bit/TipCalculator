package com.example.tipcalculator.ui.composeables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EditNumberField(
    labelText:String,
    imeAction: ImeAction,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions
){


    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions

    )
}