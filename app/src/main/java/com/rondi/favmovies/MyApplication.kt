package com.rondi.favmovies

import android.app.Application
import com.rondi.core.di.databaseModule
import com.rondi.core.di.networkModule
import com.rondi.core.di.repositoryModule
import com.rondi.favmovies.di.useCaseModule
import com.rondi.favmovies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}