package com.example.mvc_practice

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val eTvName = intent.getStringExtra("EXTRA_NAME")
        val eTvAge = intent.getIntExtra("EXTRA_AGE", 0)
        val eTvAddress = intent.getStringExtra("EXTRA_ADDR")
        val eTvOccupation = intent.getStringExtra("EXTRA_OCCU")

        val tvName = findViewById<TextView>(R.id.tvName)
        tvName.text = eTvName
        val tvAge = findViewById<TextView>(R.id.tvAge)
        tvAge.text = eTvAge.toString()
        val tvAddress = findViewById<TextView>(R.id.tvAddress)
        tvAddress.text = eTvAddress
        val tvOccupation = findViewById<TextView>(R.id.tvOccupation)
        tvOccupation.text = eTvOccupation



        val btnGoBack = findViewById<Button>(R.id.btnGoBack)
        btnGoBack.setOnClickListener{
            finish()    // Kill this activity. Values won't persist
        }

    }
}