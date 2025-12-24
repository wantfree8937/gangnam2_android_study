package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class FakeRecipeRepository : RecipeRepository {

    private val fakeRecipes = listOf(
        Recipe(
            id = 1,
            name = "Traditional spare ribs baked",
            category = "Meat",
            rating = 4.5f,
            time = "60 min",
            chef = "Chef John",
            thumbnail = ""
        ),
        Recipe(
            id = 2,
            name = "Lamb chops with fruity couscous and mint",
            category = "Meat",
            rating = 4.0f,
            time = "45 min",
            chef = "Chef Emily",
            thumbnail = ""
        ),
        Recipe(
            id = 3,
            name = "Spice roasted chicken with flavored rice",
            category = "Chicken",
            rating = 4.8f,
            time = "75 min",
            chef = "Chef Michael",
            thumbnail = ""
        ),
        Recipe(
            id = 4,
            name = "Chinese style Egg fried rice with sliced pork",
            category = "Rice",
            rating = 3.9f,
            time = "30 min",
            chef = "Chef Lee",
            thumbnail = ""
        ),
        Recipe(
            id = 5,
            name = "Vegan Stir-fry with Tofu",
            category = "Vegan",
            rating = 4.2f,
            time = "25 min",
            chef = "Chef Sophia",
            thumbnail = ""
        )
    )

    override suspend fun getRecipes(): List<Recipe> {
        return fakeRecipes
    }

    override suspend fun getRecipe(id: Int): Recipe {
        return fakeRecipes.first { it.id == id }
    }
}
