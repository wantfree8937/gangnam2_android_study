package com.survivalcoding.contacts.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT contactId FROM favorite_contacts")
    fun getAllFavoriteIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteContactEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteContactEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_contacts WHERE contactId = :id)")
    suspend fun isFavorite(id: String): Boolean
}
