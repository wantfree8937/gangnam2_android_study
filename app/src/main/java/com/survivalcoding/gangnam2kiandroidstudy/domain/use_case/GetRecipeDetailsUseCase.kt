package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class GetRecipeDetailsUseCase(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository,
    private val procedureRepository: ProcedureRepository
) {
    suspend fun execute(recipeId: Int): RecipeDetails {
        val recipe = recipeRepository.getRecipe(recipeId)
        val ingredients = ingredientRepository.getIngredients(recipeId)
        val procedures = procedureRepository.getProcedures()
        return RecipeDetails(
            recipe = recipe,
            ingredients = ingredients,
            procedures = procedures
        )
    }
}
