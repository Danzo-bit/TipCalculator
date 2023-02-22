package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.composeables.EditNumberField
import com.example.tipcalculator.ui.composeables.RoundTheTipRow
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorApp()
        }
    }
}

@Composable
fun TipCalculatorApp(){
    TipCalculatorTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
        ) {
            TipCalculatorScreen()
        }
    }
}
@Composable
fun TipCalculatorScreen(){
    var amountInput by remember {
        mutableStateOf("")
    }
    var tipInput by remember {
        mutableStateOf("")
    }
    var roundUp by remember {
        mutableStateOf(false)
    }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 15.0
    val tip = calculateTip(amount, tipPercent, roundUp)
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        EditNumberField(
            value = amountInput,
            onValueChange = {amountInput = it},
            labelText = stringResource(R.string.cost_of_service),
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    //Moves Focus keyboard direction down to next text field
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        EditNumberField(
            value = tipInput,
            onValueChange = {tipInput = it },
            labelText = stringResource(id = R.string.tip_percent),
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    //removes keypad from view
                    focusManager.clearFocus()
                }
            )
        )
        RoundTheTipRow(roundUp = roundUp, onRoundUpChanged = { roundUp = it })
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}





private fun calculateTip(
    amount: Double,
    tipPercent:Double,
    roundUp: Boolean
): String{
    var tip = tipPercent / 100 * amount
    if (roundUp)
        tip = kotlin.math.ceil(tip)
   return  NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorTheme {
        TipCalculatorScreen()
    }
}