package com.example.videoviewapp

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.MediaController
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.exo_player_control_view.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var mediaControls: MediaController? = null
    private lateinit var playerView: PlayerView
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private val url = "https://firebasestorage.googleapis.com/v0/b/simplecrud-bb3c3.appspot.com/o/Captures%202020-03-31%2013-32-47.mp4?alt=media&token=5762bfad-20c2-473d-a302-0871956be2e7"
    private val url2 = "https://drive.google.com/file/d/1gBUgJKW_joIUcUcHc4P01w6IgvqZQsw4/view"
    var fullscreen = false

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

        exoplayer_fullscreen_icon.setOnClickListener {
            if (fullscreen) {
                exoplayer_fullscreen_icon.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@Fullscreen,
                        R.drawable.ic_fullscreen_expand
                    )
                )
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                if (supportActionBar != null) {
                    supportActionBar!!.show()
                }
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                val params =
                    playerView.layoutParams as RelativeLayout.LayoutParams
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                params.height = (200 * applicationContext.resources
                    .displayMetrics.density).toInt()
                playerView.layoutParams = params
                fullscreen = false
                textView.setVisibility(View.VISIBLE)
            } else {
                exoplayer_fullscreen_icon.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@Fullscreen,
                        R.drawable.ic_fullscreen_skrink
                    )
                )
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                if (supportActionBar != null) {
                    supportActionBar!!.hide()
                }
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                val params =
                    playerView.layoutParams as RelativeLayout.LayoutParams
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                params.height = ViewGroup.LayoutParams.MATCH_PARENT
                playerView.layoutParams = params
                fullscreen = true
                textView.setVisibility(View.INVISIBLE)
            }
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
