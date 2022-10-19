package com.example.calc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var resultText: TextView
    private var firstOperator = 0.0
    private var operation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = this.findViewById(R.id.result)
    }

    fun onNumberClick(clickedView: View) {
        if (clickedView is Button) {
            var result = resultText.text.toString()
            val buttonNumber = clickedView.text.toString()

            if (result == "0") {
                result = ""
            }

            val res = result + buttonNumber
            resultText.text = res
        }
    }

    fun onOperation(view: View) {
        if (view is Button) {
            val firstNum = resultText.text
            if (firstNum.isNotEmpty()) {
                firstOperator = firstNum.toString().toDouble()
            }
            this.operation = view.text.toString()
            resultText.text = ""

            when(this.operation){
                "√" -> resultText.text = sqrt(firstOperator).toString()
                "+/-" -> resultText.text = (firstOperator * -1).toString()
            }
        }
    }

    fun equalsTo(view: View) {
        if (view is Button) {
            val secondNum = resultText.text
            var sn = 0.0
            var res = 0.0
            if (secondNum.isNotEmpty()) {
                sn = secondNum.toString().toDouble()
            }
            when (operation) {
                "+" -> res = firstOperator + sn
                "-" -> res = firstOperator - sn
                "/" -> res = firstOperator / sn
                "x" -> res = firstOperator * sn
            }

            resultText.text = res.toString()
        }
    }

    fun onClear(view: View) {
        resultText.text = "0"
        firstOperator = 0.0
        operation = ""
    }

    @SuppressLint("SetTextI18n")
    fun onFloat(view: View){
        if(resultText.text.isNotBlank() && !resultText.text.contains('.')){
            resultText.text = "${resultText.text}."
        }else{
            return
        }
    }

    fun onUndo(view: View) {
        if (resultText.text.length > 1) {
            resultText.text = resultText.text.toString().substring(0, resultText.text.length - 1)
        } else {
            resultText.text = "0"
        }
    }
}