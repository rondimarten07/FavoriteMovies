package com.rondi.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rondi.core.BuildConfig
import com.rondi.core.R
import com.rondi.core.databinding.MovieMoreItemBinding
import com.rondi.core.domain.model.Movie
import com.rondi.core.utils.DateFormatter
import com.rondi.core.utils.MovieDiffUtilCallback
import com.rondi.core.utils.loadImage

class MovieMoreListAdapter : RecyclerView.Adapter<MovieMoreListAdapter.ViewHolder>() {
    private val listMovie = ArrayList<Movie>()
    private var onClick: OnMovieListItemClickListener? = null

    fun setData(listMovie: List<Movie>) {
        val diffResult = DiffUtil.calculateDiff(MovieDiffUtilCallback(this.listMovie, listMovie))
        this.listMovie.clear()
        this.listMovie.addAll(listMovie)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnMovieListClickListener(onClick: OnMovieListItemClickListener) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieMoreListAdapter.ViewHolder {
        val binding =
            MovieMoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieMoreListAdapter.ViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    inner class ViewHolder(private val binding: MovieMoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                val roundedImage =
                    root.context.resources.getDimensionPixelSize(R.dimen.card_corners_radius) / 2
                val requestOption =
                    RequestOptions().transform(CenterCrop(), RoundedCorners(roundedImage))

                imgMoviePoster.loadImage(
                    BuildConfig.BASE_IMAGE_URL + movie.posterPath,
                    requestOption
                )
                tvMovieTitle.text = movie.title
                tvMovieVote.text = String.format("%.1f", movie.voteAverage)
                tvMovieYear.text = DateFormatter.getYearFromDateString(movie.releaseDate)
                tvMovieOverview.text = movie.overview

                binding.root.setOnClickListener { onClick?.onMovieListItemClick(movie) }
            }
        }
    }

    interface OnMovieListItemClickListener {
        fun onMovieListItemClick(movie: Movie)
    }

}