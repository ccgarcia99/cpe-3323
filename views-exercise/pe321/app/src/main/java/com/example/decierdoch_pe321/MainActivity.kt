package com.example.decierdoch_pe321

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15

class MainActivity : AppCompatActivity() {

    private lateinit var etBaseAmount: EditText
    private lateinit var sbTipPercent: SeekBar
    private lateinit var tvTipLabel: TextView
    private lateinit var tvTipCost: TextView
    private lateinit var tvBaseAndTip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etBaseAmount = findViewById(R.id.etBaseAmount)
        sbTipPercent = findViewById(R.id.sbTipPercent)
        tvTipLabel = findViewById(R.id.tvTipLevel)
        tvTipCost = findViewById(R.id.tvTipCost)
        tvBaseAndTip = findViewById(R.id.tvBaseAndTip)

        sbTipPercent.progress = INITIAL_TIP_PERCENT
        "$INITIAL_TIP_PERCENT%".also { tvTipLabel.text = it }

        sbTipPercent.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                "$p1%".also { tvTipLabel.text = it }
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //NOP
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //NOP
            }
        })
        etBaseAmount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //NOP
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //NOP
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged $p0")
                computeTipAndTotal()
            }

        })
    }

    private fun computeTipAndTotal() {
        // Parsing data from the input
        if (etBaseAmount.text.isEmpty()){
            "0.00".also { tvTipCost.text = it }
            "0.00".also { tvBaseAndTip.text = it }
        } else {
            val baseAmount = etBaseAmount.text.toString().toDouble()
            val tipPercent = sbTipPercent.progress

            // Manipulating parsed data
            val tipAmount = baseAmount * tipPercent / 100
            val totalAmount = baseAmount + tipAmount

            // Format tipAmount to 2 decimal places
            val formattedTipAmount = "%.2f".format(tipAmount)
            // Format totalAmount to 2 decimal places
            val formattedTotalAmount = "%.2f".format(totalAmount)

            // Update the UI
            tvTipCost.text = formattedTipAmount
            tvBaseAndTip.text = formattedTotalAmount
        }
    }

}