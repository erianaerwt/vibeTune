package com.vibetune.musicplayer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Layout untuk splash screen

        // Menampilkan splash screen selama 2 detik sebelum menuju ke home activity
        Handler().postDelayed({
            val intent = Intent(this, HomeActivity::class.java) // Ganti dengan activity utama Anda
            startActivity(intent)
            finish() // Tutup SplashActivity
        }, 2000) // Durasi 2 detik
    }
}
