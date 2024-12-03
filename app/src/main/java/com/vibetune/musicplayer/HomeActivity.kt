package com.vibetune.musicplayer

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vibetune.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // Initialize views

        val profileImage: ImageView = findViewById(R.id.profile)

        val homeIcon: ImageView = findViewById(R.id.home)
        val searchIcon: ImageView = findViewById(R.id.search)
        val libraryIcon: ImageView = findViewById(R.id.library)

        val playIcon: ImageView = findViewById(R.id.play)
        val likeIcon: ImageView = findViewById(R.id.heart)



        profileImage.setOnClickListener {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
        }

        homeIcon.setOnClickListener {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
        }

        searchIcon.setOnClickListener {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
        }

        libraryIcon.setOnClickListener {
            Toast.makeText(this, "Library clicked", Toast.LENGTH_SHORT).show()
        }

        playIcon.setOnClickListener {
            Toast.makeText(this, "Play clicked", Toast.LENGTH_SHORT).show()
        }

        likeIcon.setOnClickListener {
            Toast.makeText(this, "Like clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
