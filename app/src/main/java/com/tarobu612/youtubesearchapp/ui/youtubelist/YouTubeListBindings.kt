package com.tarobu612.youtubesearchapp.ui.youtubelist

import android.widget.ImageView
import android.widget.ListView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.tarobu612.youtubesearchapp.R
import com.tarobu612.youtubesearchapp.data.YouTubeInfo

object YouTubeListBindings {

    @BindingAdapter("items")
    @JvmStatic
    fun setItems(listView: ListView, items: List<YouTubeInfo>) {
        with(listView.adapter as YouTubeListAdapter) {
            replaceData(items)
        }
    }

    @BindingAdapter("url")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String) {
        Picasso.get()
            .load(url)
            .fit()
            .centerInside()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    // pass
                }

                override fun onError(e: Exception) {
                    imageView.setImageResource(R.drawable.ic_launcher_foreground)
                }
            })
    }
}