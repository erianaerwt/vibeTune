package com.vibetune.musicplayer

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class LibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library)

        // Menghubungkan View dengan ID
        val profileImage = findViewById<ImageView>(R.id.profile)
        val searchIcon = findViewById<ImageView>(R.id.search_icon)
        val addIcon = findViewById<ImageView>(R.id.add_icon)
        val playlistsTab = findViewById<Button>(R.id.playlists_tab)
        val albumsTab = findViewById<Button>(R.id.albums_tab)
        val gridView = findViewById<GridView>(R.id.grid_view)
        val homelibrary = findViewById<ImageView>(R.id.homelibrary)
        val searchlibrary = findViewById<ImageView>(R.id.searchlibrary)

        // Dummy data untuk GridView
        val playlistData = listOf(
            "Playlist 1", "Playlist 2", "Playlist 3", "Playlist 4",
            "Playlist 5", "Playlist 6"
        )

        // Adapter untuk GridView
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            playlistData
        )
        gridView.adapter = adapter

        // Event klik untuk ikon pencarian
        searchIcon.setOnClickListener {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
        }

        // Event klik untuk ikon tambah
        addIcon.setOnClickListener {
            Toast.makeText(this, "Add clicked", Toast.LENGTH_SHORT).show()
        }

        // Event klik untuk tab Playlists
        playlistsTab.setOnClickListener {
            Toast.makeText(this, "Playlists tab clicked", Toast.LENGTH_SHORT).show()
        }

        // Event klik untuk tab Albums
        albumsTab.setOnClickListener {
            Toast.makeText(this, "Albums tab clicked", Toast.LENGTH_SHORT).show()
        }

        // Event klik untuk item di GridView
        gridView.setOnItemClickListener { _, _, position, _ ->
            val playlistName = playlistData[position]
            Toast.makeText(this, "$playlistName clicked", Toast.LENGTH_SHORT).show()
        }

        // Event klik untuk profil
        profileImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        homelibrary.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
