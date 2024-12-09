package com.vibetune.musicplayer

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PopSongsActivity : AppCompatActivity() {
    private lateinit var homeIcon: ImageView
    private lateinit var searchIcon: ImageView
    private lateinit var libraryIcon: ImageView
    private lateinit var search_icon: ImageView
    private lateinit var btnSort : ImageView
    private lateinit var backButton : ImageView
    private lateinit var item_layout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lagu)

        homeIcon = findViewById(R.id.home)
        searchIcon = findViewById(R.id.search)
        libraryIcon = findViewById(R.id.library)
        search_icon = findViewById(R.id.search_icon)
        btnSort = findViewById(R.id.btnSort)
        backButton = findViewById(R.id.backButton)
        item_layout = findViewById(R.id.item_layout)

        libraryIcon.setOnClickListener {
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }

        item_layout.setOnClickListener {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }

        searchIcon.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        homeIcon.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        search_icon.setOnClickListener {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
        }

        btnSort.setOnClickListener {
            Toast.makeText(this, "Sort clicked", Toast.LENGTH_SHORT).show()
        }

        backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
