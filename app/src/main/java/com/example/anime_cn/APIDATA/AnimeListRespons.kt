package com.example.anime_cn.APIDATA

import com.google.gson.annotations.SerializedName

data class AnimeListRespons(
    val data: ArrayList<Datas>,
    val pagination: Pagination
)

data class Datas(
    val titles: List<Titles>,
    val title: String,
    val images: Imagess
)

data class Imagess(
    val jpg: Jpgg
)

data class Titles(
    val type: String,
    val title: String
)

data class Jpgg(
    @SerializedName("image_url")
    val imageUrl: String,
    val large_image_url: String,
    val small_image_url: String
)

