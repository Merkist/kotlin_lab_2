package com.example.kotlinlab2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //введення значень
        val coal_input: EditText = findViewById(R.id.input_coal)
        val oil_input: EditText = findViewById(R.id.input_oil)
        val gas_input: EditText = findViewById(R.id.input_gas)

        //результати обчислень
        val result_coal1: TextView = findViewById(R.id.res_coal1)
        val result_coal2: TextView = findViewById(R.id.res_coal2)
        val result_oil1: TextView = findViewById(R.id.res_oil1)
        val result_oil2: TextView = findViewById(R.id.res_oil2)
        val result_gas1: TextView = findViewById(R.id.res_gas1)
        val result_gas2: TextView = findViewById(R.id.res_gas2)

        val sumButton: Button = findViewById(R.id.button)

        sumButton.setOnClickListener {
            val value_coal = coal_input.text.toString()
            val value_oil = oil_input.text.toString()
            val value_gas = gas_input.text.toString()

            // перевірка на наявність значень
            if (value_coal.isNotEmpty() && value_oil.isNotEmpty() && value_gas.isNotEmpty()) {
                try {
                    val value_coal_f = value_coal.toFloat()
                    val value_oil_f = value_oil.toFloat()
                    val value_gas_f = value_gas.toFloat()

                    val q_coal = 20.47
                    val a_coal = 25.2
                    val q_oil = 39.48
                    val a_oil = 0.15
                    val q_gas = 33.08
                    val a_gas = 0

                    val k_coal = (1000000/q_coal)*0.8*(a_coal/(100-1.5))*(1-0.985)
                    val e_coal = 0.000001*k_coal*q_coal*value_coal_f

                    val k_oil = (1000000/q_oil)*1*(a_oil/(100-0))*(1-0.985)
                    val e_oil = 0.000001*k_oil*q_oil*value_oil_f

                    val k_gas = (1000000/q_gas)*0*(a_gas/(100-0))*(1-0.985)
                    val e_gas = 0.000001*k_gas*q_gas*value_gas_f


                    //вивід отриманих значень
                    result_coal1.text = String.format(Locale.US, "%.2f", k_coal)
                    result_coal2.text = String.format(Locale.US, "%.2f", e_coal)
                    result_oil1.text = String.format(Locale.US, "%.2f", k_oil)
                    result_oil2.text = String.format(Locale.US, "%.2f", e_oil)
                    result_gas1.text = String.format(Locale.US, "%.2f", k_gas)
                    result_gas2.text = String.format(Locale.US, "%.2f", e_gas)
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Введіть коректні числа.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Всі поля повинні бути заповненні.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}