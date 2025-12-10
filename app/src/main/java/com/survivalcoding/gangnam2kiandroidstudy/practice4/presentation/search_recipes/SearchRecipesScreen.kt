package com.survivalcoding.gangnam2kiandroidstudy.practice4.presentation.search_recipes

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.practice1.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSection
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RatingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.TextFilterButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipesScreen(
    viewModel: SearchRecipesViewModel = viewModel(factory = SearchRecipesViewModel.Factory),
) {
    val state by viewModel.state.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 8.dp),
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = { /* TODO: 뒤로가기 */ }
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
                onTextChanged = { viewModel.onSearchQueryChanged(it) },
            )
            Spacer(modifier = Modifier.width(20.dp))
            FilterButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                onClick = { showBottomSheet = true }
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.recipes) { recipe ->
                RecipeCard(
                    modifier = Modifier.aspectRatio(1f),
                    recipe = recipe,
                    showDetails = false
                )
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            dragHandle = null
        ) {
            var filterState by remember { mutableStateOf(FilterSearchState()) }

            val timeFilters = listOf("All", "Newest", "Oldest", "Popularity")
            val rateFilters = listOf("5", "4", "3", "2", "1")
            val categoryFilters = listOf(
                "All", "Cereal", "Vegetables", "Dinner", "Chinese",
                "Local Dish", "Fruit", "BreakFast", "Spanish", "Lunch"
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(31.dp))
                Text(
                    text = "Filter Search",
                    modifier = Modifier.fillMaxWidth(),
                    style = AppTextStyles.smallTextBold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))

                FilterSection(title = "Time") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        timeFilters.forEach {
                            TextFilterButton(
                                text = it,
                                isSelected = filterState.selectedTime == it,
                                onClick = { filterState = filterState.copy(selectedTime = it) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                FilterSection(title = "Rate") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        rateFilters.forEach {
                            RatingButton(
                                rating = it,
                                isSelected = filterState.selectedRate == it,
                                onClick = { filterState = filterState.copy(selectedRate = it) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                FilterSection(title = "Category") {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        categoryFilters.chunked(4).forEach { rowItems ->
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                rowItems.forEach {
                                    TextFilterButton(
                                        text = it,
                                        isSelected = filterState.selectedCategory == it,
                                        onClick = { filterState = filterState.copy(selectedCategory = it) }
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { /* TODO: Filter action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.primary100)
                ) {
                    Text(text = "Filter", style = AppTextStyles.mediumTextBold)
                }
                Spacer(modifier = Modifier.height(22.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchRecipesScreenPreview() {
    SearchRecipesScreen()
}
