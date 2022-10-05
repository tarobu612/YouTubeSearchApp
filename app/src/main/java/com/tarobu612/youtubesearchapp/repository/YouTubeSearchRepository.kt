package com.tarobu612.youtubesearchapp.repository

import com.tarobu612.youtubesearchapp.api.RetrofitInstance
import com.tarobu612.youtubesearchapp.model.Result
import com.tarobu612.youtubesearchapp.model.YouTubeSearchResponse
import java.lang.Exception

class YouTubeSearchRepository {

    suspend fun searchYouTube(searchQuery: String): Result<YouTubeSearchResponse, Throwable> {
        val result = runCatching {
            RetrofitInstance.api.searchYouTube(searchQuery)
        }

        val exception = result.exceptionOrNull()
        exception?.let {
            return Result.Failure(it)
        }

        val response = result.getOrNull()
        response?.let {
            if (it.body() == null) {
                return Result.Failure(Exception("response is null"))
            }

            // success
            return Result.Success(it.body()!!)
        }

        return Result.Failure(Exception("not expected exception!"))
    }
}