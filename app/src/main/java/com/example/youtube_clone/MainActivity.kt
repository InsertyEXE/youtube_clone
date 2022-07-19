package com.example.youtube_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube_clone.databinding.ActivityMainBinding
import com.example.youtube_clone.model.ListVideo
import com.example.youtube_clone.model.Video
import com.example.youtube_clone.model.VideoAdapter
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.title = ""

        val list = mutableListOf<Video>()
        videoAdapter = VideoAdapter(list){ video ->

        }

        binding.apply {
            rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
            rvMain.adapter = videoAdapter
        }

        CoroutineScope(Dispatchers.IO).launch{
            val res = async { getVideos() }
            val listOfVideo = res.await()

            withContext(Dispatchers.Main){
                listOfVideo?.let {
                    list.clear()
                    list.addAll(it.data)
                    videoAdapter.notifyDataSetChanged()
                    binding.progressRecycler.visibility = View.GONE

                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getVideos(): ListVideo? {
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().get().url("https://tiagoaguiar.co/api/youtube-videos").build()

        return try {
            val response = client.newCall(request).execute()

            if (response.isSuccessful){
                GsonBuilder().create().fromJson(response.body()?.string(), ListVideo::class.java)
            } else{
                null
            }

        } catch (e: Exception){
            null
        }
    }
}