package com.rondi.favmovies.di

import com.rondi.core.domain.usecase.MovieInteractor
import com.rondi.core.domain.usecase.MovieUseCase
import com.rondi.favmovies.ui.detail.DetailViewModel
import com.rondi.favmovies.ui.main.MainViewModel
import com.rondi.favmovies.ui.moviemore.MovieMoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { MovieMoreViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}