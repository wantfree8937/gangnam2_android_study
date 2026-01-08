package com.survivalcoding.contacts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteContactEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
