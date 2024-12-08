package com.vibetune.musicplayer

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class EditProfileActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var nameTextView: TextView
    private lateinit var fullNameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var birthDateEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialize views
        nameTextView = findViewById(R.id.tv_profile_name)
        fullNameEditText = findViewById(R.id.et_full_name)
        phoneEditText = findViewById(R.id.et_phone)
        birthDateEditText = findViewById(R.id.et_birth_day) // Adjust this based on your actual layout

        // Retrieve current user
        val currentUser = firebaseAuth.currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val database = FirebaseDatabase.getInstance().reference
            val userRef = database.child("users").child(userId)

            // Fetch current user data from Firebase
            userRef.get().addOnSuccessListener {
                if (it.exists()) {
                    val name = it.child("name").value.toString()
                    val email = currentUser.email

                    // Set data to UI
                    nameTextView.text = name
                    fullNameEditText.setText(name)
                    phoneEditText.setText(it.child("phone").value.toString()) // If available
                    birthDateEditText.setText(it.child("birthdate").value.toString()) // If available
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to load user data", Toast.LENGTH_SHORT).show()
            }
        }

        // Set back button action
        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish() // Close activity
        }

        // Save button action for updating profile

    }

    private fun updateProfile(userId: String?) {
        val newName = fullNameEditText.text.toString()
        val newPhone = phoneEditText.text.toString()
        val newBirthDate = birthDateEditText.text.toString()

        if (userId != null && newName.isNotEmpty()) {
            val database = FirebaseDatabase.getInstance().reference
            val userRef = database.child("users").child(userId)

            // Update the user profile data
            val updates = hashMapOf<String, Any>(
                "name" to newName,
                "phone" to newPhone,
                "birthdate" to newBirthDate
            )

            userRef.updateChildren(updates).addOnSuccessListener {
                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
