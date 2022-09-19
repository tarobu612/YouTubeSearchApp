package com.tarobu612.youtubesearchapp.ui.youtubelist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tarobu612.youtubesearchapp.data.YouTubeInfo
import com.tarobu612.youtubesearchapp.model.Result
import com.tarobu612.youtubesearchapp.repository.YouTubeSearchRepository
import kotlinx.coroutines.launch

class YouTubeListViewModel(
    app: Application,
    private val youTubeSearchRepository: YouTubeSearchRepository
) : AndroidViewModel(app) {

    private val _searchResults = MutableLiveData<List<YouTubeInfo>>().apply { value = emptyList() }
    val searchResults: LiveData<List<YouTubeInfo>>
        get() = _searchResults

    fun searchYouTube(searchWord: String) = viewModelScope.launch {
        loadYouTubeSearch(searchWord)
    }

    private suspend fun loadYouTubeSearch(searchWord: String) {
        when (val result = youTubeSearchRepository.searchYouTube(searchWord)) {
            is Result.Success -> {
                _searchResults.value = result.value.items.map {
                    YouTubeInfo(
                        title = it.snippet.title,
                        description = it.snippet.description,
                        thumbnail = it.snippet.thumbnails.default.url
                    )
                }
            }
            is Result.Failure -> {

            }
        }
    }
}