@file:Suppress("DEPRECATION")

package com.raza.newsheadlines.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.raza.newsheadlines.databinding.FragmentHomeBinding
import com.raza.newsheadlines.source.NewsListRepository
import com.raza.newsheadlines.source.NewsViewModelFactory
import com.raza.newsheadlines.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var topHeadlinesAdapter: TopHeadlinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //initialize the view model
        val repository = NewsListRepository(pageSize = 10)
        val vmProviderFactory = NewsViewModelFactory(repository)
        homeViewModel = ViewModelProvider(viewModelStore, vmProviderFactory)[HomeViewModel::class.java]

        topHeadlinesAdapter = TopHeadlinesAdapter { item ->
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(item.url)
            startActivity(i)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.headlines.adapter = topHeadlinesAdapter

        homeViewModel.topHeadlines.observe(viewLifecycleOwner, {
            lifecycleScope.launch { topHeadlinesAdapter.submitData(it) }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}