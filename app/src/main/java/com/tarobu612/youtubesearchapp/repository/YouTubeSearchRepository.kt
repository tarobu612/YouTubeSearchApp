package com.tarobu612.youtubesearchapp.repository

import com.tarobu612.youtubesearchapp.api.YouTubeSearchAPI

class YouTubeSearchRepository(private val api: YouTubeSearchAPI) {

    fun searchYouTube(searchQuery: String) = api.searchYouTube(searchQuery)
}