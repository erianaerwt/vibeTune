package com.vibetune.musicplayer

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)

        // App Bar
        val profileImage: ImageView = findViewById(R.id.profile)

        // Title
        val title: TextView = findViewById(R.id.title)

        // Search Bar
        val searchBar: LinearLayout = findViewById(R.id.search_bar)
        val searchInput: EditText = findViewById(R.id.search_input)
        val searchIcon: ImageView = findViewById(R.id.search_icon)

        // Recent Searches
        val recentSearchesLabel: TextView = findViewById(R.id.recent_searches_label)
        val recentSearchesList: LinearLayout = findViewById(R.id.recent_searches_list)

        // Bottom Navigation
        val bottomNav: LinearLayout = findViewById(R.id.bottomNav)
        val homeIcon: ImageView = bottomNav.findViewById(R.id.home)
        val searchIconNav: ImageView = bottomNav.findViewById(R.id.search)
        val libraryIcon: ImageView = bottomNav.findViewById(R.id.library)

        // Example usage
        profileImage.setOnClickListener {
            // Handle profile image click
        }

        searchIcon.setOnClickListener {
            val query = searchInput.text.toString()
            if (query.isNotEmpty()) {
                // Perform search operation
            }
        }

        // Handle recent search item clicks dynamically
        for (i in 0 until recentSearchesList.childCount) {
            val item = recentSearchesList.getChildAt(i)
            item.setOnClickListener {
                // Handle click on recent search item
            }
        }

        homeIcon.setOnClickListener {
            // Handle navigation to home
        }

        searchIconNav.setOnClickListener {
            // Handle navigation to search
        }

        libraryIcon.setOnClickListener {
            // Handle navigation to library
        }
    }
}
