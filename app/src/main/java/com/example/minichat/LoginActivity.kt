package com.example.minichat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.minichat.database.AppDatabase

class LoginActivity : AppCompatActivity() {

    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        db = AppDatabase.getDatabase(this)

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