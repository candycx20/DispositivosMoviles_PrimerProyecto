package com.example.primerproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        val btnEnter = findViewById<Button>(R.id.btnEnter)
        val btnSee = findViewById<Button>(R.id.btnSee)

        btnEnter.setOnClickListener() { navigateToList() }
        btnSee.setOnClickListener() { navigateToResult() }

    }

    private fun navigateToList() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToResult() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }
}