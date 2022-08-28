package com.example.youtube_clone.model

import android.content.Context
import android.net.Uri
import android.view.SurfaceHolder
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class YoutubePlayer(private val context: Context): SurfaceHolder.Callback {

    //Instanciando o exomedia
    private var mediaPlayer: SimpleExoPlayer? = null

    override fun surfaceCreated(holder: SurfaceHolder) {
        //Define qual componente ele vai usar
        if (mediaPlayer == null){
            mediaPlayer = SimpleExoPlayer.Builder(context).build()

            //Delegando pro sistema operacional usar um tocador de video
            mediaPlayer?.setVideoSurfaceHolder(holder)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        //Destruir o exomedia
        mediaPlayer?.release()
    }

    fun setUrl(url: String){
        mediaPlayer?.let {

            //default do framework pra fazer os recursos que vão tocar
            val dataSourceFactory = DefaultDataSourceFactory(
                context,
                Util.getUserAgent(context, "utube")
            )

            //Criando uma media para o exoplayer utilziar
            val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
                MediaItem.fromUri(Uri.parse(url))
            )

            it.prepare(videoSource)
            play()

        }
    }

    fun play(){
        mediaPlayer?.playWhenReady = true
    }

    fun pause() {
        mediaPlayer?.playWhenReady = false
    }

    fun release(){
        mediaPlayer?.release()
    }
}

