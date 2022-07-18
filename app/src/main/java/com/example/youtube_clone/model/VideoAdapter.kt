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

class VideoAdapter(private val list: List<Video>, val onClick: (Video) -> Unit): RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        val txtTitle = itemview.findViewById<TextView>(R.id.item_txt_title)
        val txtDesc = itemview.findViewById<TextView>(R.id.item_txt_desc)
        val imgThumbnail = itemview.findViewById<ImageView>(R.id.item_img_thumbnail)
        val imgIcon = itemview.findViewById<CircleImageView>(R.id.item_img_icon)
        val imgOptions = itemview.findViewById<ImageView>(R.id.item_img_menu)
        fun bind(video: Video) {
            with(itemView){
                setOnClickListener {
                    onClick.invoke(video)
                }

                Picasso.get().load(video.thumbnailUrl).into(imgThumbnail)
                Picasso.get().load(video.publisher.pictureProfileUrl).into(imgIcon)
                txtTitle.text = video.title
                txtDesc.text = context.getString(R.string.info, video.publisher.name, video.viewsCountLabel, video.publishedAt.formatted())
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder = VideoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        )


    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}

