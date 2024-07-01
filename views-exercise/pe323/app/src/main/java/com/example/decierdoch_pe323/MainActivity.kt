package com.example.decierdoch_pe323

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // inputs
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val etHeight = findViewById<EditText>(R.id.etHeight)
        val btnCalc = findViewById<Button>(R.id.btnCalc)

        // outputs
        val imgEval = findViewById<ImageView>(R.id.imageView)
        val tvEval = findViewById<TextView>(R.id.tvEval)

        btnCalc.setOnClickListener{
            if(etHeight.text.isEmpty() || etWeight.text.isEmpty()) {
                "Enter your weight and height below".also { tvEval.text = it }
                imgEval.setImageResource(R.drawable.chart)
                return@setOnClickListener
            }
            val weightKg = etWeight.text.toString().toDouble()
            val heightCm = etHeight.text.toString().toDouble()

            val bMI = (weightKg / heightCm.pow(2.0)) * 10000f
            val evalBMItv = when {
                bMI < 10.0 -> "Invalid"
                bMI < 18.5 -> "Underweight"
                bMI < 24.9 -> "Normal"
                bMI < 29.9 -> "Overweight"
                else -> "Obese"
            }

            val evalBMIImg = when {
                bMI < 10.0 -> imgEval.setImageResource(R.drawable.chart)
                bMI < 18.5 -> imgEval.setImageResource(R.drawable.underweight)
                bMI < 24.9 -> imgEval.setImageResource(R.drawable.normal)
                bMI < 29.9 -> imgEval.setImageResource(R.drawable.overweight)
                else -> imgEval.setImageResource(R.drawable.obese)
            }

            """
                Your BMI:
                $evalBMItv
                %.2f
            """.format(bMI).trimIndent().also { tvEval.text = it }
        }
    }
}