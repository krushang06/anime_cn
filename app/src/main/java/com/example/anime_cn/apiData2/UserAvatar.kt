package com.example.anime_cn.apiData2

import com.google.gson.annotations.SerializedName

data class UserAvatar(
    val data: ArrayList<Datas>,
    val page: Int,
    @SerializedName("per_page")
    val perpage: Int,
    val support: Support,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
data class Datas(
    val avatar: String,
    val email: String,
    @SerializedName("first_name")
    val firstname: String,
    val id: Int,
    @SerializedName("last_name")
    val lastname: String
)
data class Support(
    val text: String,
    val url: String
)
