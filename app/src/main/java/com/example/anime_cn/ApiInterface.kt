package com.example.anime_cn

import com.example.anime_cn.apiData2.UserAvatar
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
//
//interface ApiInterface {
//    @GET("anime")
//    suspend fun getAnimecartoon(@Query("page") page: String): Response<AnimeListRespons>
//}

interface ApiInterface {
    @GET("users")
    suspend fun getUser(@Query("page") page: String): Response<UserAvatar>
}