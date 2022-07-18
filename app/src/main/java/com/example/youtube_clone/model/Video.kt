package com.example.youtube_clone.model

import android.provider.ContactsContract
import java.util.*

data class Video(
    val id: String,
    val thumbnailUrl: String,
    val title: String,
    val viewsCount: Long,
    val publishedAt: Date,
    val viewCountLabel: String,
    val duration: Int,
    val videoUrl: String,
    val publisher: Publisher
)

data class Publisher(
    val id: String,
    val name: String,
    val pictureProfileUrl: String
)

data class ListVideo(
    val status: Int,
    val data: List<Video>
    )
