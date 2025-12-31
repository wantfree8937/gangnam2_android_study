package com.survivalcoding.gangnam2kiandroidstudy.data.data_source.local

import androidx.room.TypeConverter
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromIngredientsList(value: List<RecipeIngredient>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toIngredientsList(value: String): List<RecipeIngredient> {
        return Json.decodeFromString(value)
    }
}
