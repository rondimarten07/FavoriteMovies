package com.rondi.favorite.di

import com.rondi.favorite.favorite.FavoriteMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    viewModel { FavoriteMovieViewModel(get()) }

}