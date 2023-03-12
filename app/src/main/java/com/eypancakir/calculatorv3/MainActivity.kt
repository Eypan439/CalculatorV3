package com.eypancakir.calculatorv3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculator()
        }
    }
}

@Composable
fun Calculator() {
    var result by remember { mutableStateOf("") }

    Surface(color = Color.DarkGray,
        modifier = Modifier.fillMaxSize(1f)) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = result,
                onValueChange = { result = it },
                //colors = TextFieldDefaults.textFieldColors(cursorColor = Color.Black),
                textStyle = TextStyle(fontSize = 20.sp,
                    color = Color.White,
                    shadow = Shadow(
                        color = Color.LightGray, offset = Offset.Zero, blurRadius = 3f)
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CalculatorButton("1", result) { result += "1" }
                CalculatorButton("2", result) { result += "2" }
                CalculatorButton("3", result) { result += "3" }
                CalculatorButton("+", result) { result += "+" }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CalculatorButton("4", result) { result += "4" }
                CalculatorButton("5", result) { result += "5" }
                CalculatorButton("6", result) { result += "6" }
                CalculatorButton("-", result) { result += "-" }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CalculatorButton("7", result) { result += "7" }
                CalculatorButton("8", result) { result += "8" }
                CalculatorButton("9", result) { result += "9" }
                CalculatorButton("*", result) { result += "*" }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CalculatorButton("C", result) { result = "" }
                CalculatorButton("0", result) { result += "0" }
                CalculatorButton("=", result) { result = calculateResult(result) }
                CalculatorButton("/", result) { result += "/" }
            }
        }

    }


}

@Composable
fun CalculatorButton(text: String, result: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = text)
    }
}

fun calculateResult(result: String): String {
    return try {
        // Exp4j kütüphanesini kullanarak matematiksel ifadeyi hesapla
        val expression = ExpressionBuilder(result).build()
        expression.evaluate().toString()
    } catch (e: Exception) {
        // Hata durumunda geriye boş bir string döndür
        ""
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Calculator()
}