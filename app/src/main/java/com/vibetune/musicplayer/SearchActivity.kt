package com.vibetune.musicplayer


import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)

        val profileImage = findViewById<ImageView>(R.id.profile)
        val titlesearch = findViewById<TextView>(R.id.title)
        val search_input = findViewById<EditText>(R.id.search_input)
        val search_icon = findViewById<ImageView>(R.id.search_icon)
        val recent_searches_label = findViewById<TextView>(R.id.recent_searches_label)
        val item_image = findViewById<ImageView>(R.id.item_image)
        val item_title = findViewById<TextView>(R.id.item_title)
        val item_subtitle = findViewById<TextView>(R.id.item_subtitle)
        val item_remove = findViewById<ImageView>(R.id.item_remove)
        val bottomNav = findViewById<LinearLayout>(R.id.bottomNav)
        val homesearch = findViewById<ImageView>(R.id.homesearch)
        val searchbutton = findViewById<ImageView>(R.id.searchbutton)
        val librarybutton = findViewById<ImageView>(R.id.librarybutton)

        profileImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        search_input.setOnClickListener {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
        }

        search_icon.setOnClickListener {
            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
        }

        homesearch.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        librarybutton.setOnClickListener {
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }

    }
}
