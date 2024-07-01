package com.example.mvc_practice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mvc_practice.R.id.btnViewProfile

// Logcat tags
const val TAG = "onToFrags"

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

        // text inputs
        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val etOccupation = findViewById<EditText>(R.id.etOccupation)

        Intent(this, ProfileActivity::class.java).also{
            it.putExtra("EXTRA_NAME", "N/A")
            it.putExtra("EXTRA_AGE", "N/A")
            it.putExtra("EXTRA_ADDR", "N/A")
            it.putExtra("EXTRA_OCCU", "N/A")
        }

        val sendToProfile = findViewById<Button>(R.id.btnApply)
        sendToProfile.setOnClickListener{
            if (etName.text.isEmpty() || etAge.text.isEmpty() || etAddress.text.isEmpty()
                || etOccupation.text.isEmpty()) {
                return@setOnClickListener
            }
            Intent(this, ProfileActivity::class.java).also{
                it.putExtra("EXTRA_NAME", etName.text.toString())
                it.putExtra("EXTRA_AGE", etAge.text.toString().toInt())
                it.putExtra("EXTRA_ADDR", etAddress.text.toString())
                it.putExtra("EXTRA_OCCU", etOccupation.text.toString())
                startActivity(it)
            }
        }

        val btnViewProfile = findViewById<Button>(btnViewProfile)
        btnViewProfile.setOnClickListener{
            // Creating an intent
            Intent(this, ProfileActivity::class.java).also {
                startActivity(it)
            }
        }

        val btnFragsView = findViewById<Button>(R.id.btnFrags)
        btnFragsView.setOnClickListener{
            Intent(this, FragmentsSample::class.java).also {
                startActivity(it)
            }
            Log.i(TAG, "Pressed")
        }
    }
}