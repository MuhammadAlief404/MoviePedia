package com.quantumhiggs.moviepedia.core.di

import android.content.Context
import androidx.room.Room
import com.quantumhiggs.moviepedia.core.data.source.local.room.MoviePediaDatabase
import com.quantumhiggs.moviepedia.core.data.source.local.room.MoviesDao
import com.quantumhiggs.moviepedia.core.data.source.local.room.TvShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MoviePediaDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("moviePedia".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            MoviePediaDatabase::class.java, "MoviePedia.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideMoviesDao(database: MoviePediaDatabase): MoviesDao = database.moviesDao()

    @Provides
    fun provideTvShowDao(database: MoviePediaDatabase): TvShowDao = database.tvShowDao()
}