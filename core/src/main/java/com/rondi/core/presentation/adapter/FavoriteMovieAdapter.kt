package com.rondi.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rondi.core.BuildConfig
import com.rondi.core.databinding.FavoriteItemBinding
import com.rondi.core.domain.model.Movie
import com.rondi.core.utils.MovieDiffUtilCallback
import com.rondi.core.utils.loadImage

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {
    private val listFavorite = ArrayList<Movie>()
    private var onClick: OnFavoriteItemClickListener? = null

    fun setData(listFavorite: List<Movie>) {
        val diffResult =
            DiffUtil.calculateDiff(MovieDiffUtilCallback(this.listFavorite, listFavorite))
        this.listFavorite.clear()
        this.listFavorite.addAll(listFavorite)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnFavoriteClickListener(onClick: OnFavoriteItemClickListener) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listFavorite[position])

    override fun getItemCount(): Int = listFavorite.size

    inner class ViewHolder(private val binding: FavoriteItemBinding) :
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
            binding.root.setOnClickListener { onClick?.onFavoriteItemClick(movie) }
        }
    }

    interface OnFavoriteItemClickListener {
        fun onFavoriteItemClick(movie: Movie)
    }
}