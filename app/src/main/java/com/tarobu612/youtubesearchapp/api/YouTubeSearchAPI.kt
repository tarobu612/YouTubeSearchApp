package com.tarobu612.youtubesearchapp.api

import com.tarobu612.youtubesearchapp.model.YouTubeSearchResponse
import com.tarobu612.youtubesearchapp.util.YOUTUBE_API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeSearchAPI {

    @GET("v3/search")
    fun searchYouTube(
        @Query("q")
        searchQuery: String,
        @Query("type")
        searchType: String = "video",
        @Query("part")
        searchPart: String = "snippet",
        @Query("maxResults")
        maxResults: Int = 100,
        @Query("order")
        order: String = "date",
        @Query("key")
        apiKey: String = YOUTUBE_API_KEY
    ): Single<YouTubeSearchResponse>
}