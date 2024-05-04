package com.example.minichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class A2fActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a2f)

        val btnNext : Button = findViewById(R.id.btnNextA2f)
        val btnBack : Button = findViewById(R.id.btnBackA2f)

        btnNext.setOnClickListener() {
            val intent = Intent(this, ChatsActivity::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}