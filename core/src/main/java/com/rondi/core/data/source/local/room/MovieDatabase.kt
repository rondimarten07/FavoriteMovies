package com.rondi.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rondi.core.data.source.local.entity.GenreEntity
import com.rondi.core.data.source.local.entity.MovieEntity
import com.rondi.core.utils.Converters

@Database(entities = [MovieEntity::class, GenreEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}