package com.rondi.favmovies.ui.moviemore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.rondi.core.data.Resource
import com.rondi.core.domain.model.Genre
import com.rondi.core.domain.model.Movie
import com.rondi.core.presentation.MarginItemDecoration
import com.rondi.core.presentation.adapter.MovieMoreListAdapter
import com.rondi.core.utils.showToast
import com.rondi.favmovies.R
import com.rondi.favmovies.databinding.ActivityMovieMoreBinding
import com.rondi.favmovies.ui.detail.DetailActivity

class MovieMoreActivity : AppCompatActivity(), MovieMoreListAdapter.OnMovieListItemClickListener {
    private lateinit var binding: ActivityMovieMoreBinding
    private val movieListViewModel by viewModel<MovieMoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding.rvMovies) {
            val layoutManager = LinearLayoutManager(this@MovieMoreActivity)
            val verticalMargin =
                resources.getDimensionPixelSize(com.rondi.core.R.dimen.medium_margin) / 2
            val marginItemDecoration = MarginItemDecoration(verticalMargin, 0)

            this.layoutManager = layoutManager
            this.addItemDecoration(marginItemDecoration)
            this.setHasFixedSize(true)
        }

        val tag = intent.getStringExtra(EXTRA_TAG)
        tag?.let {
            var title: String? = null

            when (it) {
                POPULAR_MOVIE -> {
                    title = getString(R.string.title_popular)
                    loadPopularMovies()
                }

                TOP_RATED_MOVIE -> {
                    title = getString(R.string.title_top_rated)
                    loadTopRatedMovies()
                }

                GENRE_MOVIE -> {
                    @Suppress("DEPRECATION")
                    val genre = intent.getParcelableExtra<Genre>(EXTRA_GENRE)
                    title = getString(R.string.title_genre, genre?.name)
                }
            }

            supportActionBar?.title = title ?: "Unknown Movie Tag"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return false
    }

    private fun loadPopularMovies() {
        movieListViewModel.getPopularMovies().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    isLoading(true)
                }
                is Resource.Success -> {
                    isLoading(false)
                    val adapter = MovieMoreListAdapter()
                    adapter.setData(it.data ?: emptyList())

                    adapter.setOnMovieListClickListener(this)
                    binding.rvMovies.adapter = adapter
                }

                is Resource.Error -> {
                    isLoading(false)
                    showToast("${it.message}")
                }
            }
        }
    }

    private fun loadTopRatedMovies() {
        movieListViewModel.getTopRatedMovies().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    isLoading(true)
                }
                is Resource.Success -> {
                    isLoading(false)
                    val adapter = MovieMoreListAdapter()
                    adapter.setData(it.data ?: emptyList())

                    adapter.setOnMovieListClickListener(this)
                    binding.rvMovies.adapter = adapter
                }

                is Resource.Error -> {
                    isLoading(false)
                    showToast("${it.message}")
                }

            }
        }
    }


    override fun onMovieListItemClick(movie: Movie) {
        val intentToDetail = Intent(this, DetailActivity::class.java)
        intentToDetail.putExtra(DetailActivity.DATA_MOVIES, movie)
        startActivity(intentToDetail)
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loading.playAnimation()
        } else {
            binding.loading.cancelAnimation()
        }
    }

    companion object {
        const val POPULAR_MOVIE = "popular_movie"
        const val TOP_RATED_MOVIE = "top_rated_movie"
        const val GENRE_MOVIE = "genre_movie"
        const val EXTRA_TAG = "extra_tag"
        const val EXTRA_GENRE = "extra_genre"
    }
}