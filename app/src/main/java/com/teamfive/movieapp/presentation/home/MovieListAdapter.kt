package com.teamfive.movieapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.teamfive.movieapp.R
import com.teamfive.movieapp.data.MovieModel
import com.teamfive.movieapp.databinding.AdapterMovieItemBinding

/**
 * Created by EsraAvsar on 18.11.2023.
 */
class MovieListAdapter(
    private val movieList: ArrayList<MovieModel>
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
            Navigation.findNavController(it).navigate(R.id.action_listFragment_to_detailFragment)

//            val action = ListFragmentDirections.actionListFragmentToDetailFragment()
//            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.tvMovieTitle.text = movie.title
        Glide.with(holder.binding.ivMovie.context)
            .load(movie.image)
            .into(holder.binding.ivMovie)
    }

    override fun getItemCount(): Int = movieList.size
}
