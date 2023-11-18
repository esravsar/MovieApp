package com.teamfive.movieapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamfive.movieapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListViewModel
    private lateinit var movieAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())

        observeLiveData()

        viewModel.loadData("Batman")

        searchQueryControl()
    }

    private fun observeLiveData() {
        viewModel.movieList.observe(viewLifecycleOwner, Observer { movies ->
            binding.tvErrorText.visibility = View.GONE
            binding.rvMovie.visibility = View.VISIBLE
            movieAdapter = MovieListAdapter(ArrayList(movies.data))
            binding.rvMovie.adapter = movieAdapter
        })

        viewModel.movieError.observe(viewLifecycleOwner, Observer { error ->
            error.data?.let {
                if (it) {
                    binding.tvErrorText.visibility = View.VISIBLE
                    binding.rvMovie.visibility = View.GONE
                } else {
                    binding.tvErrorText.visibility = View.GONE
                }
            }
        })

        viewModel.movieLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading.data?.let {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvMovie.visibility = View.GONE
                    binding.tvErrorText.visibility = View.GONE
                    lifecycleScope.launch {
                        delay(3000)
                        binding.tvErrorText.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun searchQueryControl() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                searchMovie(text)
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                searchMovie(text)
                return true
            }
        })
    }

    private fun searchMovie(searchText: String?) {
        if (searchText != null) {
            viewModel.loadData(searchText)
        }
    }
}