package com.tarobu612.youtubesearchapp.ui.youtubelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.tarobu612.youtubesearchapp.data.YouTubeInfo
import com.tarobu612.youtubesearchapp.databinding.YoutubeListItemBinding

class YouTubeListAdapter(
    private var youTubeResults: List<YouTubeInfo>
) : BaseAdapter() {
    override fun getCount() = youTubeResults.size

    override fun getItem(position: Int) = youTubeResults[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        val binding = if (view == null) {
            YoutubeListItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        } else {
            // Recycling view
            DataBindingUtil.getBinding(view) ?: throw IllegalStateException("recycling view is null")
        }

        binding.youtubeInfo = youTubeResults[position]
        binding.executePendingBindings()

        return binding.root
    }

    fun replaceData(results: List<YouTubeInfo>) {
        setList(results)
    }

    private fun setList(results: List<YouTubeInfo>) {
        this.youTubeResults = results
        notifyDataSetChanged()
    }
}