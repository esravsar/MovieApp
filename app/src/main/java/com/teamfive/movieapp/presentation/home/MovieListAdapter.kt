package com.teamfive.movieapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.teamfive.movieapp.databinding.AdapterMovieItemBinding
import com.teamfive.movieapp.domain.model.Movie

/**
 * Created by EsraAvsar on 18.11.2023.
 */
class MovieListAdapter(
    private val movieList: ArrayList<Movie>
) : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    class MovieHolder(val binding: AdapterMovieItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemBinding =
            AdapterMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movieList.get(position)

        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(movie.imdbID)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.tvMovieTitle.text = movie.Title
        holder.binding.tvMovieRelease.text = movie.Year
        Glide.with(holder.binding.ivMovie.context)
            .load(movie.Poster)
            .into(holder.binding.ivMovie)
    }

    override fun getItemCount(): Int = movieList.size
}
