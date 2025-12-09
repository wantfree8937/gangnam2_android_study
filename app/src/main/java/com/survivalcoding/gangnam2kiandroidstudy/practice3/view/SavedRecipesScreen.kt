package com.survivalcoding.gangnam2kiandroidstudy.practice3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.practice1.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.practice3.viewmodel.SavedRecipesViewModel

@Composable
fun SavedRecipesScreen(
    viewModel: SavedRecipesViewModel = viewModel(factory = SavedRecipesViewModel.Factory)
) {
    val recipes by viewModel.recipes.collectAsState()
    SavedRecipesContent(recipes = recipes)
}

@Composable
fun SavedRecipesContent(recipes: List<Recipe>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Saved Recipes",
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recipes) { recipe ->
                RecipeCard(recipe = recipe)
            }
        }
        BottomNavigationBar()
    }
}

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* TODO: Handle navigation */ }) {
            Icon(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "Home",
                tint = Color.LightGray
            )
        }
        IconButton(onClick = { /* TODO: Handle navigation */ }) {
            Icon(
                painter = painterResource(id = R.drawable.inactive),
                contentDescription = "Saved",
                tint = Color.LightGray
            )
        }
        IconButton(onClick = { /* TODO: Handle navigation */ }) {
            Icon(
                painter = painterResource(id = R.drawable.notification_bing),
                contentDescription = "Notifications",
                tint = Color.LightGray
            )
        }
        IconButton(onClick = { /* TODO: Handle navigation */ }) {
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                tint = Color.LightGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedRecipesScreenPreview() {
    val sampleRecipes = listOf(
        Recipe(
            id = 1,
            category = "Indian",
            name = "Traditional spare ribs baked",
            image = "https://images.unsplash.com/photo-1598515213692-5f2824124933?q=80&w=2070&auto=format&fit=crop",
            chef = "Chef John",
            time = "20 min",
            rating = 4.0,
            ingredients = emptyList()
        ),
        Recipe(
            id = 2,
            category = "Asian",
            name = "Spice roasted chicken with flavored rice",
            image = "https://images.unsplash.com/photo-1598515213692-5f2824124933?q=80&w=2070&auto=format&fit=crop",
            chef = "Mark Kelvin",
            time = "20 min",
            rating = 4.0,
            ingredients = emptyList()
        )
    )

    Surface(color = Color.White) {
        SavedRecipesContent(recipes = sampleRecipes)
    }
}
