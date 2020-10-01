package com.example.videoviewapp

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import javax.sql.DataSource


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var mediaControls: MediaController? = null
    private lateinit var playerView: PlayerView
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private val url = "https://firebasestorage.googleapis.com/v0/b/simplecrud-bb3c3.appspot.com/o/Captures%202020-03-31%2013-32-47.mp4?alt=media&token=5762bfad-20c2-473d-a302-0871956be2e7"
    private val url2 = "https://drive.google.com/file/d/1gBUgJKW_joIUcUcHc4P01w6IgvqZQsw4/view"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()


//        val videoView = findViewById<VideoView>(R.id.videoView)
//        if (mediaControls == null) {
//            // create an object of media controller class
//            mediaControls = MediaController(this);
//            mediaControls!!.setAnchorView(videoView);
//        }
//        // set the media controller for video view
//        videoView.setMediaController(mediaControls);
//        val uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/simplecrud-bb3c3.appspot.com/o/Captures%202020-03-31%2013-32-47.mp4?alt=media&token=5762bfad-20c2-473d-a302-0871956be2e7")
//        videoView.setVideoURI(uri)
//        videoView.requestFocus()
//        videoView.start()
//
//        videoView.setOnPreparedListener(MediaPlayer.OnPreparedListener {
//            Toast.makeText(applicationContext, "Dimulai", Toast.LENGTH_LONG).show()
//        })
//        videoView.setOnCompletionListener(OnCompletionListener {
//            Toast.makeText(applicationContext, "Thank You...!!!", Toast.LENGTH_LONG).show()
//        })

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

        initExoPlayer()

        btn_full.setOnClickListener {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    fun setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun initExoPlayer(){
        playerView = findViewById(R.id.video_view)
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this)
        playerView.player = simpleExoPlayer
        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "appname"))
        val videoSource = ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(url))
        simpleExoPlayer.prepare(videoSource)
        simpleExoPlayer.playWhenReady = true

    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.release()
    }
}
