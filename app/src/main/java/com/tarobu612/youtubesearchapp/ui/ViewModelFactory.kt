package com.tarobu612.youtubesearchapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tarobu612.youtubesearchapp.repository.YouTubeSearchRepository
import com.tarobu612.youtubesearchapp.ui.youtubelist.YouTubeListViewModel

class ViewModelFactory(
    private val app: Application,
    private val youTubeSearchRepository: YouTubeSearchRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return YouTubeListViewModel(app, youTubeSearchRepository) as T
    }
}