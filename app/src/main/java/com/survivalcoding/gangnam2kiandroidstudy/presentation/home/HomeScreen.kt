package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard2
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard3
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCategorySelector
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    onSearchClick: () -> Unit,
    onRecipeClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Hello Jega",
                    style = AppTextStyles.largeTextBold.copy(
                        color = AppColors.black
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "What are you cooking today?",
                    style = AppTextStyles.smallTextRegular2.copy(
                        color = AppColors.gray3
                    )
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(AppColors.secondary40)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.jega_face),
                    contentDescription = "Jega Face"
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchInputField(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                text = "",
                onTextChanged = {},
                enabled = false,
                onClick = onSearchClick
            )
            Spacer(modifier = Modifier.width(20.dp))
            FilterButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        RecipeCategorySelector(
            modifier = Modifier.padding(horizontal = 30.dp),
            selectedCategory = state.selectedCategory,
            onCategorySelected = { category -> onAction(HomeAction.CategorySelected(category)) },
        )
        Spacer(modifier = Modifier.height(25.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(231.dp),
            contentPadding = PaddingValues(horizontal = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.filteredHomeRecipes) { homeRecipe ->
                RecipeCard2(
                    recipeName = homeRecipe.recipe.name,
                    time = homeRecipe.recipe.time,
                    rating = homeRecipe.recipe.rating,
                    imageUrl = homeRecipe.recipe.image,
                    isBookmarked = homeRecipe.isBookmarked,
                    onCardClick = { onRecipeClick(homeRecipe.recipe.id) },
                    onBookmarkClick = { onAction(HomeAction.RecipeBookmarked(homeRecipe.recipe)) }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            text = "New Recipes",
            style = AppTextStyles.normalTextBold.copy(
                color = AppColors.black
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            contentPadding = PaddingValues(horizontal = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.homeRecipes) { homeRecipe ->
                RecipeCard3(
                    recipeName = homeRecipe.recipe.name,
                    rating = homeRecipe.recipe.rating,
                    chefImageUrl = homeRecipe.chefImageUrl,
                    chefName = homeRecipe.recipe.chef,
                    time = homeRecipe.recipe.time,
                    recipeImageUrl = homeRecipe.recipe.image,
                    onClick = { onRecipeClick(homeRecipe.recipe.id) }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
