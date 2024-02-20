package com.example.calkutator20

import java.math.BigInteger
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    var button_one: Button? = null
    var button_two: Button? = null
    var button_three: Button? = null
    var button_four: Button? = null
    var button_five: Button? = null
    var button_six: Button? = null
    var button_seven: Button? = null
    var button_eight: Button? = null
    var button_nine: Button? = null
    var button_zero: Button? = null
    var button_dot: Button? = null
    var button_equal: Button? = null
    var button_addition: Button? = null
    var button_subtraction: Button? = null
    var button_multiplication: Button? = null
    var button_division: Button? = null
    var button_reverse: Button? = null
    var button_open_parenthesis: Button? = null
    var button_close_parenthesis: Button? = null
    var button_square_root: Button? = null
    var button_square: Button? = null
    var button_percent: Button? = null
    var button_factorial: Button? = null
    var button_clear_entry: Button? = null
    var button_delete: Button? = null
    var result_text: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_one = findViewById<Button>(R.id.button_one)
        button_two = findViewById<Button>(R.id.button_two)
        button_three = findViewById<Button>(R.id.button_three)
        button_four = findViewById<Button>(R.id.button_four)
        button_five = findViewById<Button>(R.id.button_five)
        button_six = findViewById<Button>(R.id.button_six)
        button_seven = findViewById<Button>(R.id.button_seven)
        button_eight = findViewById<Button>(R.id.button_eight)
        button_nine = findViewById<Button>(R.id.button_nine)
        button_zero = findViewById<Button>(R.id.button_zero)
        button_dot = findViewById<Button>(R.id.button_dot)
        button_equal = findViewById<Button>(R.id.button_equal)
        button_addition = findViewById<Button>(R.id.button_addition)
        button_subtraction = findViewById<Button>(R.id.button_subtraction)
        button_multiplication = findViewById<Button>(R.id.button_multiplication)
        button_division = findViewById<Button>(R.id.button_division)
        button_percent = findViewById<Button>(R.id.button_percent)
        button_delete = findViewById<Button>(R.id.button_delete)
        button_clear_entry = findViewById<Button>(R.id.button_clear_entry)
        result_text = findViewById<TextView>(R.id.result_text)
        button_reverse = findViewById<Button>(R.id.button_reverse)
        button_square_root = findViewById<Button>(R.id.button_square_root)
        button_square = findViewById<Button>(R.id.button_square)
        button_factorial = findViewById<Button>(R.id.button_factorial)
        button_open_parenthesis = findViewById<Button>(R.id.button_open_parenthesis)
        button_close_parenthesis = findViewById<Button>(R.id.button_close_parenthesis)

        var isNewNumber = true

        savedInstanceState?.getString("textViewKey")?.let { text ->
            val textView = findViewById<TextView>(R.id.result_text)
            textView.text = text
        }

        button_one?.setOnClickListener {
            clearError()
            result_text?.text = "${result_text?.text}1"
        }

        button_two?.setOnClickListener {
            clearError()
            result_text?.text = "${result_text?.text}2"
        }

        button_three?.setOnClickListener {
            clearError()
            result_text?.text = "${result_text?.text}3"
        }

        button_four?.setOnClickListener {
            clearError()
            result_text?.text = "${result_text?.text}4"
        }

        button_five?.setOnClickListener {
            clearError()
            result_text?.text = "${result_text?.text}5"
        }

        button_six?.setOnClickListener {
            clearError()
            result_text?.text = "${result_text?.text}6"
        }

        button_seven?.setOnClickListener {
            clearError()
            result_text?.text = "${result_text?.text}7"
        }

        button_eight?.setOnClickListener {
            clearError()
            result_text?.text = "${result_text?.text}8"
        }

        button_nine?.setOnClickListener {
            clearError()
            result_text?.text = "${result_text?.text}9"
        }

        button_zero?.setOnClickListener {
            clearError()
            result_text?.text = "${result_text?.text}0"
        }

        button_dot?.setOnClickListener {
        if (isNewNumber) {
            result_text?.text = "${result_text?.text}."
            isNewNumber = false
        }
        else if(result_text?.text.toString().contains(".")) {
            isNewNumber = false
        }

        }

        button_clear_entry?.setOnClickListener {
            result_text?.text = ""
        }

        button_delete?.setOnClickListener {
            val text = result_text?.text.toString()
            if (text.isNotEmpty()) {
                result_text?.text = text.substring(0, text.length - 1)
            }
        }

        button_addition?.setOnClickListener {
            val currentText = result_text?.text.toString()
            if (!isLastCharacterOperation(currentText)) {
                result_text?.text = "$currentText+"
            } else {
                result_text?.text = currentText.dropLast(1) + "+"
            }
            isNewNumber = true
        }

        button_subtraction?.setOnClickListener {
            val currentText = result_text?.text.toString()
            if (!isLastCharacterOperation(currentText)) {
                result_text?.text = "$currentText-"
            } else {
                result_text?.text = currentText.dropLast(1) + "-"
            }
            isNewNumber = true
        }

        button_multiplication?.setOnClickListener {
            val currentText = result_text?.text.toString()
            if (!isLastCharacterOperation(currentText)) {
                result_text?.text = "$currentText×"
            } else {
                result_text?.text = currentText.dropLast(1) + "×"
            }
            isNewNumber = true
        }

        button_division?.setOnClickListener {
            val currentText = result_text?.text.toString()
            if (!isLastCharacterOperation(currentText)) {
                result_text?.text = "$currentText÷"
            } else {
                result_text?.text = currentText.dropLast(1) + "÷"
            }
            isNewNumber = true
        }

        button_percent?.setOnClickListener {
            val expression = result_text?.text.toString()
            val operators = arrayOf('+', '-', '*', '/')

            for (operator in operators.reversed()) {
                val operatorIndex = expression.lastIndexOf(operator)
                if (operatorIndex != -1) {
                    try {
                        val firstNumber = expression.substring(0, operatorIndex).toDouble()
                        val secondNumber = expression.substring(operatorIndex + 1).toDouble() / 100
                        val percentResult = when (expression[operatorIndex]) {
                            '+' -> firstNumber + firstNumber * secondNumber
                            '-' -> firstNumber - firstNumber * secondNumber
                            '*' -> firstNumber * (firstNumber * secondNumber)
                            '/' -> firstNumber / (firstNumber * secondNumber)
                            else -> 0.0
                        }
                        result_text?.text = percentResult.toString()
                        return@setOnClickListener
                    } catch (e: Exception) {
                        result_text?.text = "Ошибка"
                        return@setOnClickListener
                    }
                }
            }
            try {
                val number = expression.toDouble()
                result_text?.text = (number / 100).toString()
            } catch (e: Exception) {
                result_text?.text = "Ошибка"
            }
        }

        button_square_root?.setOnClickListener {
            val currentExpression = result_text?.text.toString()

            if (isParenthesesBalanced(currentExpression)) {
                try {
                    val result = Calculator.eval(currentExpression)
                    val rootResult = sqrt(result)
                    result_text?.text = rootResult.toString()
                } catch (e: Exception) {
                    result_text?.text = "Ошибка"
                }
            } else {
            
                Toast.makeText(this, "Необходимо закрыть все скобки перед извлечением корня", Toast.LENGTH_SHORT).show()
            }
        }

        button_open_parenthesis?.setOnClickListener {
            result_text?.text = "${result_text?.text}("
        }

        button_close_parenthesis?.setOnClickListener {
            val text = result_text?.text.toString()
            if (isValidCloseParenthesis(text)) {
                result_text?.text = "$text)"
            } else {
                result_text?.text="Ошибка"
            }
        }

        button_reverse?.setOnClickListener {
            if(result_text?.text?.isEmpty() == true || result_text?.text?.isBlank() == true){
                result_text?.text = "Ошибка"
            }else {
                result_text?.text = "${result_text?.text}^(-1)"
            }
        }

        button_factorial?.setOnClickListener {
            if (result_text?.text?.isEmpty() == true || result_text?.text?.isBlank() == true) {
                result_text?.text = "Ошибка"
            } else if (result_text?.text == "Ошибка") {
                result_text?.text = ""
                return@setOnClickListener
            } else {
                val value = result_text?.text.toString().toIntOrNull()

                if (value == null || value < 0) {
                    
                    result_text?.text = "Ошибка"
                } else {
                    
                    val factorialResult = factorial(value)
                    result_text?.text = factorialResult.toString()
                }
            }
        }


        button_square?.setOnClickListener {
            if(result_text?.text?.isEmpty() == true || result_text?.text?.isBlank() == true){
                result_text?.text = "Ошибка"
            }else {
                result_text?.text = "${result_text?.text}^(2)"
            }
        }

        button_equal?.setOnClickListener {
            try {
                val value = result_text?.text.toString()
                if (value.contains("Ошибка")) {
                    result_text?.text = ""
                    return@setOnClickListener
                }
                val replacedStr = value.replace('÷', '/').replace('×', '*')
                val result = Calculator.eval(replacedStr)
                result_text?.text = result.toString()
            } catch (e: Exception) {
                result_text?.text = "Ошибка"
            }

            /*val value = result_text?.text.toString()
            val replacedStr = value.replace('÷', '/').replace('×', '*')
            val result = Calculator.eval(replacedStr)
            result_text?.text = result.toString()*/
        }

    }

    fun isValidCloseParenthesis(text: String): Boolean {
        var openCount = 0
        var closeCount = 0
        for (char in text) {
            if (char == '(') openCount++
            if (char == ')') closeCount++
        }
        return openCount > closeCount
    }

    fun isParenthesesBalanced(expression: String): Boolean {
        var balance = 0
        for (char in expression) {
            if (char == '(') balance++
            if (char == ')') balance--
            if (balance < 0) return false 
        }
        return balance == 0 
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val textView = findViewById<TextView>(R.id.result_text)
        outState.putString("textViewKey", textView.text.toString())
    }

    fun isLastCharacterOperation(text: String): Boolean {
        if (text.isEmpty()) return false
        val lastChar = text.last()
        return lastChar == '+' || lastChar == '-' || lastChar == '×' || lastChar == '÷' || lastChar == '.'
    }

    fun factorial(n: Int): BigInteger {
        var result = BigInteger.ONE
        for (i in 1..n) {
            result = result.multiply(BigInteger.valueOf(i.toLong()))
        }
        return result
    }

    private fun clearError() {
        if (result_text?.text.toString() == "Ошибка") {
            result_text?.text = ""
        }
    }

    object Calculator {

        fun eval(str: String): Double {
            return object {
                var pos = -1
                var ch: Int = 0

                fun nextChar() {
                    ch = if (++pos < str.length) str[pos].code else -1
                }

                fun eat(charToEat: Int): Boolean {
                    while (ch == ' '.code) nextChar()
                    if (ch == charToEat) {
                        nextChar()
                        return true
                    }
                    return false
                }

                fun parse(): Double {
                    nextChar()
                    val x = parseExpression()
                    if (pos < str.length) throw RuntimeException("Unexpected: ${ch.toChar()}")
                    return x
                }

                fun parseExpression(): Double {
                    var x = parseTerm()
                    while (true) {
                        x = when {
                            eat('+'.code) -> x + parseTerm() 
                            eat('-'.code) -> x - parseTerm() 
                            else -> return x
                        }
                    }
                }

                fun parseTerm(): Double {
                    var x = parseFactor()
                    while (true) {
                        x = when {
                            eat('*'.code) -> x * parseFactor() 
                            eat('/'.code) -> {
                                val divisor = parseFactor()
                            if (divisor == 0.0) throw ArithmeticException("деление на ноль невозможно")
                                    x / divisor}
                            else -> return x
                        }
                    }
                }

                fun parseFactor(): Double {
                    var x: Double
                    val startPos = pos
                    when {
                        eat('+'.code) -> x = parseFactor() 
                        eat('-'.code) -> x = -parseFactor() 
                        eat('('.code) -> {
                            x = parseExpression()
                            if (!eat(')'.code)) throw RuntimeException("Missing ')'")
                        }

                        ch in '0'.code..'9'.code || ch == '.'.code -> { // numbers
                            while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                            x = str.substring(startPos, pos).toDouble()
                        }
                        ch in 'a'.code..'z'.code -> { // functions
                            while (ch in 'a'.code..'z'.code) nextChar()
                            val func = str.substring(startPos, pos)
                            x = parseFactor()
                            x = when (func) {
                                "sqrt" -> sqrt(x)
                                else -> throw RuntimeException("Unknown function: $func")
                            }
                        }
                        else -> throw RuntimeException("Unexpected: ${ch.toChar()}")
                    }
                    if (eat('^'.code)) x = x.pow(parseFactor())
                    return x
                }
            }.parse()
        }
    }


}