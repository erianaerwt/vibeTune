package com.vibetune.musicplayer  // Pastikan nama package ini sesuai dengan yang di AndroidManifest.xml

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.vibetune.R

class MainActivity : AppCompatActivity() {

    // Mendeklarasikan variabel untuk komponen UI
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var text1: TextView
    private lateinit var text2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Pastikan nama layoutnya sesuai dengan file XML Anda

        // Inisialisasi komponen UI
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)

        // Set aksi untuk tombol login
        loginButton.setOnClickListener {
            // Menavigasi ke halaman login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Set aksi untuk tombol register
        registerButton.setOnClickListener {
            // Menavigasi ke halaman register
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
