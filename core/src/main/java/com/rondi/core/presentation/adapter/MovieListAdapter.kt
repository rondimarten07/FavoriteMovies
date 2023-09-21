package com.rondi.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rondi.core.BuildConfig
import com.rondi.core.databinding.MovieItemBinding
import com.rondi.core.domain.model.Movie
import com.rondi.core.utils.MovieDiffUtilCallback
import com.rondi.core.utils.loadImage

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val listMovie = ArrayList<Movie>()
    private var onClick: OnMovieItemClickListener? = null

    fun setData(listMovie: List<Movie>) {
        val diffResult = DiffUtil.calculateDiff(MovieDiffUtilCallback(this.listMovie, listMovie))
        this.listMovie.clear()
        this.listMovie.addAll(listMovie)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnMovieClickListener(onClick: OnMovieItemClickListener) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listMovie[position])

    override fun getItemCount(): Int = listMovie.size

    inner class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                val requestOption = RequestOptions().transform(CenterCrop(), RoundedCorners(32))
                imgMoviePoster.loadImage(
                    BuildConfig.BASE_IMAGE_URL + movie.posterPath,
                    requestOption
                )
                tvMovieTitle.text = movie.title

            }
            binding.root.setOnClickListener { onClick?.onMovieItemClick(movie) }
        }
    }

    interface OnMovieItemClickListener {
        fun onMovieItemClick(movie: Movie)
    }
}