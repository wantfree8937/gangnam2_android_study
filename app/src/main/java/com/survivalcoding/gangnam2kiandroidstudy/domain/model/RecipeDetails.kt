package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class RecipeDetails(
    val recipe: Recipe,
    val ingredients: List<Ingredient>,
    val procedures: List<Procedure>
)