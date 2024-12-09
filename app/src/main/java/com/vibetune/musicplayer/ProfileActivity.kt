package com.vibetune.musicplayer

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ProfileActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil) // Pastikan layout sesuai dengan file XML Anda

        // Inisialisasi FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Inisialisasi View
        val backButton = findViewById<ImageView>(R.id.backButton)
        val editButton = findViewById<ImageView>(R.id.editButton)
        val logoutButton = findViewById<TextView>(R.id.logout)
        val helpSupport = findViewById<TextView>(R.id.helpSupport)

        // Profil Data View
        val usernameTextView = findViewById<TextView>(R.id.userName)
        val emailTextView = findViewById<TextView>(R.id.info_email)
        val phoneNumberTextView = findViewById<TextView>(R.id.info_phone)
        val birthdayTextView = findViewById<TextView>(R.id.info_birthDate)

        // Ambil data pengguna dari Firebase Authentication
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            // Ambil ID pengguna untuk mengambil data lebih lanjut dari Firebase Realtime Database
            val userId = currentUser.uid

            // Referensi ke Firebase Realtime Database untuk mengambil data pengguna
            val database = FirebaseDatabase.getInstance().reference
            val userRef = database.child("users").child(userId)

            userRef.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val userName = snapshot.child("name").value.toString()
                    val userEmail = snapshot.child("email").value.toString()

                    // Ambil phone number dan birthday, dengan default value jika kosong
                    val phoneNumber = snapshot.child("phone").value?.toString() ?: "No Phone Number"
                    val birthday = snapshot.child("birthdate").value?.toString() ?: "No Birthday"

                    // Set data pada TextView
                    usernameTextView.text = userName
                    emailTextView.text = userEmail
                    phoneNumberTextView.text = phoneNumber
                    birthdayTextView.text = birthday
                } else {
                    Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to load user data.", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Jika tidak ada pengguna yang login
            usernameTextView.text = "Guest"
            emailTextView.text = "Not Logged In"
            phoneNumberTextView.text = "No Phone Number"
            birthdayTextView.text = "No Birthday"
        }

        // Event: Kembali ke halaman sebelumnya
        backButton.setOnClickListener {
            finish() // Menutup aktivitas ini dan kembali ke sebelumnya
        }

        // Event: Edit Profil (Belum ada fungsi tambahan)
        editButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        // Event: Logout
        logoutButton.setOnClickListener {
            firebaseAuth.signOut()

            // Menavigasi ke halaman login setelah logout
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Membersihkan aktivitas sebelumnya
            startActivity(intent)
            finish()
        }
    }
}
