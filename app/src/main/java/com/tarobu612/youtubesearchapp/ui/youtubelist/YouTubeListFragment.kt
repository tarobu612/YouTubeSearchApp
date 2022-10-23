package com.tarobu612.youtubesearchapp.ui.youtubelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tarobu612.youtubesearchapp.R
import com.tarobu612.youtubesearchapp.api.RetrofitInstance
import com.tarobu612.youtubesearchapp.databinding.YoutubeListFragmentBinding
import com.tarobu612.youtubesearchapp.repository.YouTubeSearchRepository
import com.tarobu612.youtubesearchapp.ui.MainActivity
import com.tarobu612.youtubesearchapp.ui.ViewModelFactory

class YouTubeListFragment : Fragment(), SearchViewListener {
    private lateinit var youTubeListViewModel: YouTubeListViewModel
    private lateinit var binding: YoutubeListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val youTubeSearchRepository = YouTubeSearchRepository(RetrofitInstance.api)
        val viewModelFactory =
            ViewModelFactory(requireActivity().application, youTubeSearchRepository)
        youTubeListViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[YouTubeListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = YoutubeListFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = youTubeListViewModel
        }

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.adapter = YouTubeListAdapter(
            youTubeResults = listOf()
        )

        binding.viewModel?.showToast?.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        val activity = requireActivity() as MainActivity
        val searchView = activity.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(SearchViewQueryTextListener(this))
    }

    override fun onSearch(query: String) {
        binding.viewModel?.searchYouTube(query)
    }
}