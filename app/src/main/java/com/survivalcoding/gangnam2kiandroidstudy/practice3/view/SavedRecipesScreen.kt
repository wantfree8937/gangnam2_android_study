package com.survivalcoding.gangnam2kiandroidstudy.practice3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

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
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
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
            .height(90.dp)
            .background(Color.White)
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top
    ) {
        IconButton(onClick = { /* TODO: Handle navigation */ }) {
            Icon(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "Home",
                modifier = Modifier.size(24.dp),
                tint = AppColors.gray4
            )
        }
        IconButton(onClick = { /* TODO: Handle navigation */ }) {
            Icon(
                painter = painterResource(id = R.drawable.inactive),
                contentDescription = "Saved",
                modifier = Modifier.size(24.dp),
                tint = AppColors.gray4
            )
        }
        IconButton(onClick = { /* TODO: Handle navigation */ }) {
            Icon(
                painter = painterResource(id = R.drawable.notification_bing),
                contentDescription = "Notifications",
                modifier = Modifier.size(24.dp),
                tint = AppColors.gray4
            )
        }
        IconButton(onClick = { /* TODO: Handle navigation */ }) {
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier.size(24.dp),
                tint = AppColors.gray4
            )
        }
    }
}
