package com.tarobu612.youtubesearchapp.api

import com.tarobu612.youtubesearchapp.util.YOUTUBE_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {
    companion object {

        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(YOUTUBE_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
        }

        val api: YouTubeSearchAPI by lazy {
            retrofit.create(YouTubeSearchAPI::class.java)
        }
    }
}