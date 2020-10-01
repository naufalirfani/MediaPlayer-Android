package com.example.videoviewapp

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoView = findViewById(R.id.videoView) as VideoView
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        val uri =
            Uri.parse("https://firebasestorage.googleapis.com/v0/b/video-app-af9bf.appspot.com/o/Videos%2FVideo_20200812_141638_.mp4?alt=media&token=d87f94e0-6ca3-4de2-856c-b228884185f7")
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
//        mWebView.getSettings().setJavaScriptEnabled(true)
//        mWebView.getSettings().setPluginState(PluginState.ON)
//        mWebView.loadUrl("https://www.youtube.com/watch?v=NHXUM-6a3dU&list=RDNHXUM-6a3dU&start_radio=1")
//        mWebView.setWebChromeClient(WebChromeClient())

//        startActivity(
//            Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("https://www.youtube.com/watch?v=NHXUM-6a3dU&list=RDNHXUM-6a3dU&start_radio=1")
//            )
//        )
//        Log.i("Video", "Video Playing....")
    }
}
