package com.example.minichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnSingIn : Button = findViewById(R.id.btnSignin)
        val btnSingUp : Button = findViewById(R.id.btnSignup)

        btnSingIn.setOnClickListener() {
            val intent = Intent(this, A2fActivity::class.java)
            startActivity(intent)
        }

        btnSingUp.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}