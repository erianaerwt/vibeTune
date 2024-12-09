package com.vibetune.musicplayer

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class HomeActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var profileImage: ImageView
    private lateinit var greetingText: TextView
    private lateinit var homeIcon: ImageView
    private lateinit var searchIcon: ImageView
    private lateinit var libraryIcon: ImageView
    private lateinit var playIcon: ImageView
    private lateinit var likeIcon: ImageView
    private lateinit var pop: ImageView
    private lateinit var musikplay : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialize views
        profileImage = findViewById(R.id.profile)
        greetingText = findViewById(R.id.greetingText)
        homeIcon = findViewById(R.id.home)
        searchIcon = findViewById(R.id.search)
        libraryIcon = findViewById(R.id.library)
        playIcon = findViewById(R.id.play)
        likeIcon = findViewById(R.id.heart)
        pop = findViewById(R.id.popimage)
        musikplay = findViewById(R.id.musikplay)


        // Get current user info from Firebase
        val user = firebaseAuth.currentUser
        if (user != null) {
            // Get user name and profile image URL from Firebase Realtime Database
            val database = FirebaseDatabase.getInstance().reference
            val userRef = database.child("users").child(user.uid)

            userRef.get().addOnSuccessListener {
                if (it.exists()) {
                    val userName = it.child("name").value.toString()
                    val profileUrl = it.child("profileUrl").value.toString()

                    // Set greeting and profile image
                    greetingText.text = "Hello, $userName!"
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to load user data.", Toast.LENGTH_SHORT).show()
            }
        }

        // Set listeners
        profileImage.setOnClickListener {
            // Go to profile page for editing
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        pop.setOnClickListener {
            val intent = Intent(this, PopSongsActivity::class.java)
            startActivity(intent)
        }

        homeIcon.setOnClickListener {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
        }

        searchIcon.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        likeIcon.setOnClickListener {
            Toast.makeText(this, "Like clicked", Toast.LENGTH_SHORT).show()
        }

        libraryIcon.setOnClickListener {
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }

        musikplay.setOnClickListener {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }
    }
}
