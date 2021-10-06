@file:Suppress("DEPRECATION")

package com.raza.newsheadlines.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.raza.newsheadlines.databinding.FragmentHomeBinding
import com.raza.newsheadlines.ui.viewmodel.HomeViewModel


@Suppress("DEPRECATION")
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
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
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

        homeViewModel.getHeadlines().observe(viewLifecycleOwner, {
            topHeadlinesAdapter.submitList(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}