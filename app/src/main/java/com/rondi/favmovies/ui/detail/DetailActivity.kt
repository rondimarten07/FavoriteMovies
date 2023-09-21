package com.rondi.favmovies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.rondi.core.domain.model.Movie
import com.rondi.core.utils.MovieMapper
import com.rondi.core.utils.showImageInto
import com.rondi.favmovies.R.string
import com.rondi.favmovies.R.drawable
import com.rondi.favmovies.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModel<DetailViewModel>()
    private var statusFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(string.title_detail)
        val movies = intent.getParcelableExtra<Movie>(DATA_MOVIES)

        binding.apply {
            imgMovieBackdrop.showImageInto(this@DetailActivity, movies?.backdropPath)
            imgMoviePoster.showImageInto(this@DetailActivity, movies?.posterPath)
            tvMovieOverview.text = movies?.overview
            tvMovieTitle.text = movies?.title
            tvMovieVote.text = movies?.voteAverage.toString()
            tvMovieDate.text = movies?.releaseDate
            movies?.genreIds?.let {
                MovieMapper.mapGenreIdToGenre(it)
                    .filterNotNull()
                    .take(3)
                    .forEach { tvMovieGenre.append(" \u2023 $it") }
            }

            movies?.id?.let { movieId ->
                detailViewModel.isFavoriteMovie(movieId)
                    .observe(this@DetailActivity, favoriteObserver)
            }

            binding.btnFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                movies?.id?.let { movieId ->
                    detailViewModel.update(movieId, statusFavorite)
                }

                setFavoriteStatus(statusFavorite)
            }
        }
    }

    private val favoriteObserver = Observer<Boolean> { isFavorite ->
        this.statusFavorite = isFavorite
        setFavoriteStatus(isFavorite)
    }

    private fun setFavoriteStatus(statusFavorite: Boolean) {
        val favoriteBtn = binding.btnFavorite
        if (statusFavorite) {
            favoriteBtn.setImageResource(drawable.ic_favorite_fill)
        } else {
            favoriteBtn.setImageResource(drawable.ic_favorite_border)
        }
    }

    companion object {
        const val DATA_MOVIES = "movie"
    }

}