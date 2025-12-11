package com.survivalcoding.gangnam2kiandroidstudy.practice4.presentation.search_recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.practice1.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipesScreen(
    state: SearchRecipesState,
    onQueryChange: (String) -> Unit,
    onFilterChange: (FilterSearchState) -> Unit,
    onShowFilter: () -> Unit,
    showBottomSheet: Boolean,
    onDismissBottomSheet: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 30.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 8.dp),
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = { /* 껍데기 */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "뒤로가기"
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Search recipes",
                style = AppTextStyles.mediumTextBold
            )
        }
        Spacer(modifier = Modifier.height(17.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchInputField(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                text = state.searchQuery,
                onTextChanged = onQueryChange,
            )
            Spacer(modifier = Modifier.width(20.dp))
            FilterButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f), onClick = onShowFilter
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (state.searchQuery.isBlank()) "Recent Search" else "Search Result",
                style = AppTextStyles.normalTextBold,
            )
            if (state.searchQuery.isNotBlank()) {
                Text(
                    text = "${state.recipes.size} results",
                    style = AppTextStyles.smallTextRegular2,
                    color = AppColors.gray3
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(state.recipes) { recipe ->
                    RecipeCard(
                        modifier = Modifier.aspectRatio(1f), recipe = recipe, showDetails = false
                    )
                }
            }
        }
    }

    if (showBottomSheet) {
        FilterSearchBottomSheet(
            onDismissRequest = onDismissBottomSheet,
            onFilter = onFilterChange,
            initialState = state.appliedFilters
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchRecipesScreenPreview() {
    SearchRecipesScreen(
        state = SearchRecipesState(
            recipes = listOf(
                Recipe(
                    id = 1,
                    category = "dessert",
                    name = "Korean BBQ",
                    image = "",
                    chef = "Gordon Ramsay",
                    time = "15 Mins",
                    rating = 4.5,
                    ingredients = listOf()
                ), Recipe(
                    id = 2,
                    category = "main course",
                    name = "Bibimbap",
                    image = "",
                    chef = "John Torode",
                    time = "20 Mins",
                    rating = 4.2,
                    ingredients = listOf()
                )
            )
        ),
        onQueryChange = {},
        onFilterChange = {},
        onShowFilter = {},
        showBottomSheet = false,
        onDismissBottomSheet = {}
    )
}
