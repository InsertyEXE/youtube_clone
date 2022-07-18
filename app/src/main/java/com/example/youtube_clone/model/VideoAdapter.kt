package com.example.youtube_clone.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_clone.R
import de.hdodenhof.circleimageview.CircleImageView

class VideoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class VideoViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        val txtTitle = itemview.findViewById<TextView>(R.id.item_txt_title)
        val txtDesc = itemview.findViewById<TextView>(R.id.item_txt_desc)
        val imgThumbnail = itemview.findViewById<ImageView>(R.id.item_img_thumbnail)
        val imgIcon = itemview.findViewById<CircleImageView>(R.id.item_img_icon)
        val imgOptions = itemview.findViewById<ImageView>(R.id.item_img_menu)
        fun bind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = VideoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        )


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 10
}