package com.example.minichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnSingUp : Button = findViewById(R.id.btnSignUpReg)
        val btnBack : Button = findViewById(R.id.btnBackReg)

        btnSingUp.setOnClickListener() {
            val intent = Intent(this, ChatsActivity::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}