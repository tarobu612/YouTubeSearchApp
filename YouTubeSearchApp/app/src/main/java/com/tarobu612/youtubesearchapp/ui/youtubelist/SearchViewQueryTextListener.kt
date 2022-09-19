package com.tarobu612.youtubesearchapp.ui.youtubelist

import androidx.appcompat.widget.SearchView

class SearchViewQueryTextListener(private val listener: SearchViewListener) : SearchView.OnQueryTextListener {
    override fun onQueryTextChange(newText: String?): Boolean {
        // 入力された文字が変更された
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // 文字の入力後に、検索ボタンが押された
        query?.let {
            listener.onSearch(it)
        }

        return false
    }
}