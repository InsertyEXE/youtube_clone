package com.example.youtube_clone.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_clone.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class VideoContentAdapter(private val list: List<Video>): RecyclerView.Adapter<VideoContentAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        val txtTitle = itemview.findViewById<TextView>(R.id.video_detail_title)
        val txtDesc = itemview.findViewById<TextView>(R.id.video_detail_info)
        val imgThumbnail = itemview.findViewById<ImageView>(R.id.video_detail_thumbnail)
        val imgOptions = itemview.findViewById<ImageView>(R.id.video_detail_menu)
        fun bind(video: Video) {
            with(itemView){

                Picasso.get().load(video.thumbnailUrl).into(imgThumbnail)
                txtTitle.text = video.title
                txtDesc.text = context.getString(R.string.info, video.publisher.name, video.viewsCountLabel, video.publishedAt.formatted())
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder = VideoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.video_detail_list_item_video, parent, false)
        )


    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}

