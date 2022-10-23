package com.tarobu612.youtubesearchapp.ui.youtubelist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tarobu612.youtubesearchapp.data.YouTubeInfo
import com.tarobu612.youtubesearchapp.repository.YouTubeSearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class YouTubeListViewModel(
    app: Application,
    private val youTubeSearchRepository: YouTubeSearchRepository
) : AndroidViewModel(app) {
    private val compositeDisposable = CompositeDisposable()

    private val _searchResults = MutableLiveData<List<YouTubeInfo>>().apply { value = emptyList() }
    val searchResults: LiveData<List<YouTubeInfo>>
        get() = _searchResults

    private val _showToast = MutableLiveData<String>()
    val showToast: LiveData<String>
        get() = _showToast

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun searchYouTube(searchWord: String) {
        compositeDisposable += youTubeSearchRepository.searchYouTube(searchWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { res ->
                    _searchResults.value = res.items.map {
                        YouTubeInfo(
                            title = it.snippet.title,
                            description = it.snippet.description,
                            thumbnail = it.snippet.thumbnails.default.url
                        )
                    }
                },
                onError = { e ->
                    _showToast.value = "API Error: $e"
                }
            )
    }
}