package com.example.viewsdemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        // constants
        var tipMultiplier = 0f

        // input
        val btnCalculateTip = findViewById<Button>(R.id.btnCalculateTip)
        val etEnterBill = findViewById<EditText>(R.id.etBillField)
        val radioSelect = findViewById<RadioGroup>(R.id.tipGroup)

        // output
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val tvTipCost = findViewById<TextView>(R.id.tvTipCostOut)

        radioSelect.setOnCheckedChangeListener{ _, checkedId ->
            tipMultiplier = when (checkedId) {
                R.id.rdGood -> 0.10f
                R.id.rdGreat -> 0.15f
                R.id.rdAmazing -> 0.25f
                else -> 0f
            }
        }

        btnCalculateTip.setOnClickListener{
            if(etEnterBill.text.isEmpty()) {
                "PHP 0.00".also { tvTotal.text = it }
                "PHP 0.00".also {tvTipCost.text = it}
                return@setOnClickListener
            }
            val baseAmount = etEnterBill.text.toString().toDouble() // parse string into double
            val tipAmount = baseAmount * tipMultiplier
            val totalAmount = baseAmount + tipAmount
            "PHP %.2f".format(totalAmount).also { tvTotal.text = it }
            "PHP %.2f".format(tipAmount).also { tvTipCost.text= it }
        }

    }
}