package com.teamfive.movieapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.teamfive.movieapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val bundle: DetailFragmentArgs by navArgs()
        val incomingimdbID = bundle.imdbID

        viewModel.getMovieDetail(incomingimdbID)

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            binding.tvDetailMovieTitle.text = it.data?.Title
            binding.tvDetailRelease.text = it.data?.Released
            binding.tvDetailActors.text = it.data?.Actors
            binding.tvDetailCountry.text = it.data?.Country
            binding.tvDetailDirector.text = it.data?.Director
            binding.tvDetailIMDB.text = it.data?.imdbRating
            Glide.with(requireContext())
                .load(it.data?.Poster)
                .into(binding.ivDetailMovie)
        })
    }
}