package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecipeCategorySelector(
    modifier: Modifier = Modifier,
    onCategorySelected: (String) -> Unit,
    selectedCategory: String,
    categories: List<String> = listOf("All",
        "Indian", "Italian", "Asian", "Chinese", "Fruit",
        "Vegetables", "Protein", "Cereal", "Local Dishes"
    )
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(categories) { category ->
            TextFilterButton(
                text = category,
                isSelected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                textModifier = Modifier.padding(horizontal = 15.dp, vertical = 7.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeCategorySelectorPreview() {
    var selectedCategory by remember { mutableStateOf("Lunch") }

    RecipeCategorySelector(
        selectedCategory = selectedCategory,
        onCategorySelected = { selectedCategory = it }
    )
}
