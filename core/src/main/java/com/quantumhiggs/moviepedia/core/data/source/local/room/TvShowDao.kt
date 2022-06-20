package com.quantumhiggs.moviepedia.core.data.source.local.room

import androidx.room.*
import com.quantumhiggs.moviepedia.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvShow")
    fun getAllTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvShow WHERE is_favorite = 1")
    fun getAllTvShowFavorites(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuckTvShow(tvShows: List<TvShowEntity>)

    @Update
    fun updateFavoriteTvShow(tvShow: TvShowEntity)
}