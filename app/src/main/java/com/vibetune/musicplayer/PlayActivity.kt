package com.vibetune.musicplayer

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PlayActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var menuButton: ImageView
    private lateinit var heart: ImageView
    private lateinit var playButton: ImageView
    private lateinit var nextButton: ImageView
    private lateinit var previousButton: ImageView
    private lateinit var shuffleButton: ImageView

    private lateinit var progressBar: SeekBar
    private lateinit var currentTimeText: TextView
    private lateinit var endTimeText: TextView

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play)

        // Initialize buttons, progress bar and media player
        backButton = findViewById(R.id.back_button)
        menuButton = findViewById(R.id.menu_button)
        heart = findViewById(R.id.heart_button)
        playButton = findViewById(R.id.play_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        shuffleButton = findViewById(R.id.shuffle_button)

        progressBar = findViewById(R.id.progress_bar)
        currentTimeText = findViewById(R.id.current_time)
        endTimeText = findViewById(R.id.end_time)

        // Initialize media player with a music file from res/raw
        mediaPlayer = MediaPlayer.create(this, R.raw.nina) // Replace 'nina' with your actual file name
        mediaPlayer?.setOnCompletionListener {
            // Handle when the song completes
            Toast.makeText(this, "Song Finished", Toast.LENGTH_SHORT).show()
            stopMusic()
        }

        // Set initial time
        updateTimeDisplay()

        // Update progress bar and timestamps as the song plays
        progressBar.max = mediaPlayer?.duration ?: 0
        progressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Optional: Pause music when user starts dragging
                mediaPlayer?.pause()
                isPlaying = false
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Resume music when user finishes dragging
                mediaPlayer?.seekTo(seekBar?.progress ?: 0)
                if (isPlaying) {
                    mediaPlayer?.start()
                }
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }
        })

        // Initialize buttons functionality
        backButton.setOnClickListener {
            val intent = Intent(this, PopSongsActivity::class.java)
            startActivity(intent)
        }

        menuButton.setOnClickListener {
            Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show()
        }

        heart.setOnClickListener {
            Toast.makeText(this, "Heart clicked", Toast.LENGTH_SHORT).show()
        }

        playButton.setOnClickListener {
            if (isPlaying) {
                pauseMusic()
            } else {
                playMusic()
            }
        }

        nextButton.setOnClickListener {
            Toast.makeText(this, "Next clicked", Toast.LENGTH_SHORT).show()
            // Implement next song functionality here
        }

        previousButton.setOnClickListener {
            Toast.makeText(this, "Previous clicked", Toast.LENGTH_SHORT).show()
            // Implement previous song functionality here
        }

        shuffleButton.setOnClickListener {
            Toast.makeText(this, "Shuffle clicked", Toast.LENGTH_SHORT).show()
            // Implement shuffle functionality here
        }
    }

    private fun playMusic() {
        mediaPlayer?.start()
        playButton.setImageResource(R.drawable.pause_putih) // Change the play button to a pause icon
        isPlaying = true
        Toast.makeText(this, "Music Playing", Toast.LENGTH_SHORT).show() // Debugging toast
        updateProgressBar()
    }

    private fun pauseMusic() {
        mediaPlayer?.pause()
        playButton.setImageResource(R.drawable.play_putih) // Change the pause button back to play icon
        isPlaying = false
        Toast.makeText(this, "Music Paused", Toast.LENGTH_SHORT).show() // Debugging toast
        updateProgressBar()
    }

    private fun stopMusic() {
        mediaPlayer?.stop()
        playButton.setImageResource(R.drawable.play_putih) // Change the pause button back to play icon
        isPlaying = false
        updateProgressBar()
    }

    private fun updateProgressBar() {
        // Update progress bar position and timestamps
        val currentPosition = mediaPlayer?.currentPosition ?: 0
        progressBar.progress = currentPosition
        val minutes = currentPosition / 1000 / 60
        val seconds = currentPosition / 1000 % 60
        currentTimeText.text = String.format("%02d:%02d", minutes, seconds)

        val totalDuration = mediaPlayer?.duration ?: 0
        val totalMinutes = totalDuration / 1000 / 60
        val totalSeconds = totalDuration / 1000 % 60
        endTimeText.text = String.format("%02d:%02d", totalMinutes, totalSeconds)
    }

    private fun updateTimeDisplay() {
        // Update the time display on screen every second
        val handler = android.os.Handler()
        val updateTimeTask = object : Runnable {
            override fun run() {
                updateProgressBar()
                handler.postDelayed(this, 1000) // Update every second
            }
        }
        handler.post(updateTimeTask)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release media player resources when activity is destroyed
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
