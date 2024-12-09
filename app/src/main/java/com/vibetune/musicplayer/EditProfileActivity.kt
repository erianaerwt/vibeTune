package com.vibetune.musicplayer

import android.os.Bundle
import android.widget.Button
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
    private lateinit var et_email: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var birthDateEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var birthMonthEditText: EditText
    private lateinit var birthYearEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialize views
        nameTextView = findViewById(R.id.tv_profile_name)
        fullNameEditText = findViewById(R.id.et_full_name)
        et_email = findViewById(R.id.et_email)
        phoneEditText = findViewById(R.id.et_phone)
        birthDateEditText = findViewById(R.id.et_birth_day) // For day input
        saveButton = findViewById(R.id.button_save)
        birthYearEditText = findViewById(R.id.et_birth_year) // For year input
        birthMonthEditText = findViewById(R.id.et_birth_month) // For month input

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
                    et_email.setText(email)
                    phoneEditText.setText(it.child("phone").value.toString()) // If available
                    val birthDate = it.child("birthdate").value.toString()
                    // Set birthdate (split to day, month, year)
                    if (birthDate.isNotEmpty()) {
                        val parts = birthDate.split("-")
                        if (parts.size == 3) {
                            birthDateEditText.setText(parts[0]) // Day
                            birthMonthEditText.setText(parts[1]) // Month
                            birthYearEditText.setText(parts[2]) // Year
                        }
                    }
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

        saveButton.setOnClickListener {
            updateProfile(userId)
        }
    }

    private fun updateProfile(userId: String?) {
        val newName = fullNameEditText.text.toString()
        val newPhone = phoneEditText.text.toString()
        val newDay = birthDateEditText.text.toString()
        val newMonth = birthMonthEditText.text.toString()
        val newYear = birthYearEditText.text.toString()

        // Validate birthdate input
        if (newDay.isEmpty() || newMonth.isEmpty() || newYear.isEmpty()) {
            Toast.makeText(this, "Please complete your birthdate", Toast.LENGTH_SHORT).show()
            return
        }

        // Validate birthdate format
        if (newDay.toIntOrNull() == null || newMonth.toIntOrNull() == null || newYear.toIntOrNull() == null) {
            Toast.makeText(this, "Invalid birthdate input", Toast.LENGTH_SHORT).show()
            return
        }

        val newBirthDate = "$newDay-$newMonth-$newYear"

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
