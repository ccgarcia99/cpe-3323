package com.example.decierdoch_pe322

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Quote(val quote: String, val author: String)

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val quotes = listOf(
            Quote("Life is like riding a bicycle. To keep your balance you must keep moving.", "Albert Einstein"),
            Quote("The best way to predict the future is to invent it.", "Alan Kay"),
            Quote("Life is 10% what happens to us and 90% how we react to it.", "Charles R. Swindoll"),
            Quote("The only way to do great work is to love what you do.", "Steve Jobs"),
            Quote("If you can dream it, you can achieve it.", "Zig Ziglar"),
            Quote("The best revenge is massive success.", "Frank Sinatra"),
            Quote("Your time is limited, don’t waste it living someone else’s life.", "Steve Jobs"),
            Quote("Whether you think you can or you think you can’t, you’re right.", "Henry Ford"),
            Quote("The only limit to our realization of tomorrow is our doubts of today.", "Franklin D. Roosevelt"),
            Quote("Don’t watch the clock; do what it does. Keep going.", "Sam Levenson")
        )

        val tvQuote = findViewById<TextView>(R.id.tvQuote)
        val btnGetNewQuote = findViewById<Button>(R.id.btnGetQuote)

        // Display a random quote initially
        val initialQuote = quotes.random()
        tvQuote.text =
        """
            "${initialQuote.quote}"
            - ${initialQuote.author}
        """.trimIndent()

        // Set up button click listener to display a new random quote
        btnGetNewQuote.setOnClickListener {
            val newQuote = quotes.random()
            tvQuote.text =
            """
                "${newQuote.quote}"
                - ${newQuote.author}
            """.trimIndent()
        }
    }
}
