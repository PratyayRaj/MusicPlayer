package com.example.musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class MusicPlayerActivity : AppCompatActivity() {

    private val songs = intArrayOf(
        R.raw.untitled,
        R.raw.kaise_hua,
        R.raw.kesariya,
        R.raw.dandelions
    )
    private var currentSongIndex = 0
    private var isPlaying = false
    private var mediaPlayer: MediaPlayer? = null

    // Declare UI components (as in the previous example)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        // Initialize UI components (as in the previous example)

        prevButton.setOnClickListener {
            playPreviousSong()
        }

        playPauseButton.setOnClickListener {
            togglePlayPause()
        }

        nextButton.setOnClickListener {
            playNextSong()
        }
    }

    private fun playPreviousSong() {
        if (currentSongIndex > 0) {
            currentSongIndex--
            updateUIForCurrentSong()
            playMusic()
        }
    }

    private fun playNextSong() {
        if (currentSongIndex < songs.size - 1) {
            currentSongIndex++
            updateUIForCurrentSong()
            playMusic()
        }
    }

    private fun togglePlayPause() {
        if (isPlaying) {
            pauseMusic()
        } else {
            playMusic()
        }
    }

    private fun playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex])
            mediaPlayer?.setOnCompletionListener {
                // Handle playback completion, e.g., play the next song
                playNextSong()
            }
        }
        mediaPlayer?.start()
        isPlaying = true
        playPauseButton.setImageResource(R.drawable.ic_pause)
    }

    private fun pauseMusic() {
        mediaPlayer?.pause()
        isPlaying = false
        playPauseButton.setImageResource(R.drawable.ic_play)
    }

    private fun updateUIForCurrentSong() {
        // Update UI components to display information about the current song
        songTitleTextView.text = resources.getResourceEntryName(songs[currentSongIndex])
        // You can update other UI elements like album art, artist name, etc.
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}

