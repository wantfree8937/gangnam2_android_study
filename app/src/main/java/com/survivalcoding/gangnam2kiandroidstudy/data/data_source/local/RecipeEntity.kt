package com.survivalcoding.gangnam2kiandroidstudy.data.data_source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: Int,
    val category: String,
    val name: String,
    val image: String,
    val chef: String,
    val time: String,
    val rating: Double,
    val ingredients: List<RecipeIngredient>
) {
    fun toDomain(): Recipe {
        return Recipe(
            id = id,
            category = category,
            name = name,
            image = image,
            chef = chef,
            time = time,
            rating = rating,
            ingredients = ingredients
        )
    }

    companion object {
        fun fromDomain(recipe: Recipe): RecipeEntity {
            return RecipeEntity(
                id = recipe.id,
                category = recipe.category,
                name = recipe.name,
                image = recipe.image,
                chef = recipe.chef,
                time = recipe.time,
                rating = recipe.rating,
                ingredients = recipe.ingredients
            )
        }
    }
}
