package com.rondi.favmovies.ui.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rondi.core.data.Resource
import com.rondi.core.domain.model.Genre
import com.rondi.core.domain.model.Movie
import com.rondi.core.presentation.MarginItemDecoration
import com.rondi.core.presentation.adapter.GenreListAdapter
import com.rondi.core.presentation.adapter.MovieListAdapter
import com.rondi.core.utils.showToast
import com.rondi.favmovies.ui.moviemore.MovieMoreActivity
import com.rondi.favmovies.R
import com.rondi.favmovies.databinding.ActivityMainBinding
import com.rondi.favmovies.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), MovieListAdapter.OnMovieItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var marginItemDecoration: MarginItemDecoration
    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 2f

        val horizontalMargin =
            resources.getDimensionPixelSize(com.rondi.core.R.dimen.medium_margin) / 2
        marginItemDecoration = MarginItemDecoration(0, horizontalMargin)

        binding.rvCategory.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.rvCategory.addItemDecoration(marginItemDecoration)
        binding.rvCategory.setHasFixedSize(true)


        mainViewModel.getGenres().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    isLoading(true)
                }

                is Resource.Success -> {
                    isLoading(false)
                    showGenres(it.data ?: emptyList())
                }
                is Resource.Error -> {
                    isLoading(false)
                    showToast("${it.message}")
                }
            }
        }

        mainViewModel.getNowPlayingMovies().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    isLoading(true)
                }
                is Resource.Success -> {
                    isLoading(false)
                    showMovies(it.data ?: emptyList(), binding.rvNowPlaying)
                }
                is Resource.Error -> {
                    isLoading(false)
                    showToast("${it.message}")
                }
            }
        }

        mainViewModel.getTopRatedMovies().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    isLoading(true)
                }

                is Resource.Success -> {
                    isLoading(false)
                    showMovies(it.data ?: emptyList(), binding.rvTopRated)
                }
                is Resource.Error -> {
                    isLoading(false)
                    showToast("${it.message}")
                }
            }
        }

        mainViewModel.getPopularMovies().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    isLoading(true)
                }

                is Resource.Success -> {
                    isLoading(false)
                    showMovies(it.data ?: emptyList(), binding.rvPopular)
                }
                is Resource.Error -> {
                    isLoading(false)
                    showToast("${it.message}")
                }
            }
        }

        buttonMore()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite_menu -> {
                val uri = Uri.parse("favoritemovie://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun showGenres(genres: List<Genre>) {
        if (genres.isNotEmpty()) {
            val adapter = GenreListAdapter()
            adapter.setData(genres)

            binding.rvCategory.adapter = adapter
        }
    }

    private fun showMovies(movies: List<Movie>, recyclerView: RecyclerView) {
        if (movies.isNotEmpty()) {
            val adapter = MovieListAdapter()
            adapter.setData(movies)
            adapter.setOnMovieClickListener(this)

            recyclerView.addItemDecoration(marginItemDecoration)
            recyclerView.layoutManager = LinearLayoutManager(this).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
    }

    private fun buttonMore() {
        val movieListIntent = Intent(this, MovieMoreActivity::class.java)

        binding.btnShowMorePopular.setOnClickListener {
            movieListIntent.putExtra(MovieMoreActivity.EXTRA_TAG, MovieMoreActivity.POPULAR_MOVIE)
            startActivity(movieListIntent)
        }

        binding.btnShowMoreTopRated.setOnClickListener {
            movieListIntent.putExtra(MovieMoreActivity.EXTRA_TAG, MovieMoreActivity.TOP_RATED_MOVIE)
            startActivity(movieListIntent)
        }
    }

    override fun onMovieItemClick(movie: Movie) {
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
}