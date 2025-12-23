package com.survivalcoding.gangnam2kiandroidstudy.presentation.saved_recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SavedRecipesScreen(
    recipes: List<Recipe>,
    onRecipeClick: (Int) -> Unit,
    onBookmarkClick: (Recipe) -> Unit,
    listState: LazyListState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Saved recipes",
            modifier = Modifier.padding(10.dp),
            style = AppTextStyles.mediumTextBold
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(recipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = { onRecipeClick(recipe.id) },
                    onBookmarkClick = { onBookmarkClick(recipe) },
                    isBookmarked = true,
                    nameTextStyle = AppTextStyles.smallTextBold
                )
            }
        }
    }
}
