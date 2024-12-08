package com.vibetune.musicplayer

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil) // Pastikan file XML-nya benar

        // Inisialisasi View
        val backButton = findViewById<ImageView>(R.id.backButton)
        val editButton = findViewById<ImageView>(R.id.editButton)
        val logoutButton = findViewById<TextView>(R.id.logout)
        val helpSupport = findViewById<TextView>(R.id.helpSupport)

        // Event: Kembali ke halaman sebelumnya
        backButton.setOnClickListener {
            finish() // Menutup aktivitas ini dan kembali ke sebelumnya
        }


        // Event: Logout
        logoutButton.setOnClickListener {
            // Tambahkan logika logout, seperti menghapus session pengguna
            val intent = Intent(this, LoginActivity::class.java) // Menuju halaman login
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Membersihkan aktivitas sebelumnya
            startActivity(intent)
        }

    }
}
