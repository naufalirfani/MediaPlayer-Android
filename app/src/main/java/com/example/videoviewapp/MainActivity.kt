package com.example.videoviewapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val videoView: VideoView = findViewById(R.id.videoView)
//        val mediaController = MediaController(this)
//        mediaController.setAnchorView(videoView)
//        videoView.setMediaController(mediaController)
//        videoView.setVideoURI(Uri.parse("youtube.com/watch?v=NHXUM-6a3dU&list=RDNHXUM-6a3dU&start_radio=1"))
//        videoView.start()

        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=NHXUM-6a3dU&list=RDNHXUM-6a3dU&start_radio=1")
            ) 
        )
        Log.i("Video", "Video Playing....")
    }
}
