package com.rondi.favorite.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rondi.core.domain.model.Movie
import com.rondi.core.presentation.MarginItemDecoration
import com.rondi.core.presentation.adapter.FavoriteMovieAdapter
import com.rondi.favmovies.ui.detail.DetailActivity
import com.rondi.favorite.databinding.ActivityFavoriteMovieBinding
import com.rondi.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteMovieActivity : AppCompatActivity(),
    FavoriteMovieAdapter.OnFavoriteItemClickListener {
    private val favoriteMoviesViewModel by viewModel<FavoriteMovieViewModel>()
    private lateinit var marginItemDecoration: MarginItemDecoration

    private lateinit var _binding: ActivityFavoriteMovieBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = getString(com.rondi.favmovies.R.string.favorite)
        favoriteObserver()

    }

    private fun favoriteObserver() {
        favoriteMoviesViewModel.getFavoriteMovies().observe(this) {
            Log.d("data", "it")
            if (it.isEmpty()) {
                binding.rvFavoriteMovie.visibility = View.INVISIBLE
                binding.tvErrorMessage.visibility = View.VISIBLE
            } else {
                binding.rvFavoriteMovie.visibility = View.VISIBLE
                binding.tvErrorMessage.visibility = View.GONE
                showMovies(it, binding.rvFavoriteMovie)
            }
        }
    }

    private fun showMovies(listFavorite: List<Movie>, recyclerView: RecyclerView) {
        val horizontalMargin =
            resources.getDimensionPixelSize(com.rondi.core.R.dimen.medium_margin) / 2
        marginItemDecoration = MarginItemDecoration(0, horizontalMargin)

        if (listFavorite.isNotEmpty()) {
            val adapter = FavoriteMovieAdapter()
            adapter.setData(listFavorite)
            adapter.setOnFavoriteClickListener(this)

            recyclerView.layoutManager = GridLayoutManager(this, 2)
            recyclerView.addItemDecoration(marginItemDecoration)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
    }

    override fun onFavoriteItemClick(movie: Movie) {
        val intentToDetail = Intent(this, DetailActivity::class.java)
        intentToDetail.putExtra(DetailActivity.DATA_MOVIES, movie)
        startActivity(intentToDetail)
    }
}