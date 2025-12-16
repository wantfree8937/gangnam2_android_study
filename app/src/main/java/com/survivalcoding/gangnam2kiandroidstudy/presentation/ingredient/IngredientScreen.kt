package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ProcedureStepItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun IngredientScreen(
    recipe: Recipe,
    procedures: List<Procedure>,
    onBack: () -> Unit,
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "Back"
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.union),
                    contentDescription = "More options"
                )
            }
        }
        RecipeCard(
            modifier = Modifier.fillMaxWidth(),
            recipe = recipe,
            showRecipeName = false,
            showChefName = false,
            onClick = {},
            isBookmarked = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = recipe.name,
                style = AppTextStyles.smallTextBold,
                color = AppColors.black
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "(13k Reviews)",
                style = AppTextStyles.labelTextRegular,
                color = AppColors.gray3
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Chef profile picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = recipe.chef, style = AppTextStyles.normalTextBold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "Location",
                            modifier = Modifier.size(14.dp),
                            tint = AppColors.primary80
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Lagos, Nigeria",
                            style = AppTextStyles.smallTextRegular2,
                            color = AppColors.gray3
                        )
                    }
                }
            }
            SmallButton(
                modifier = Modifier.width(90.dp),
                text = "Follow",
                onClick = { /*TODO*/ },
                isSelected = true
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallButton(
                modifier = Modifier.weight(1f),
                text = "Ingredient",
                onClick = { onTabSelected("Ingredient") },
                isSelected = selectedTab == "Ingredient"
            )
            SmallButton(
                modifier = Modifier.weight(1f),
                text = "Procedure",
                onClick = { onTabSelected("Procedure") },
                isSelected = selectedTab == "Procedure"
            )
        }
        Spacer(modifier = Modifier.height(35.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.serve),
                contentDescription = "Serve",
                tint = AppColors.gray3
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "1 serve", color = AppColors.gray3)
            Spacer(modifier = Modifier.weight(1f))
            val itemsText = if (selectedTab == "Ingredient") {
                "${recipe.ingredients.size} Items"
            } else {
                "${procedures.size} Steps"
            }
            Text(text = itemsText, color = AppColors.gray3)
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (selectedTab == "Ingredient") {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(recipe.ingredients) { ingredient ->
                    IngredientItem(recipeIngredient = ingredient)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(procedures) { procedure ->
                    ProcedureStepItem(
                        stepNumber = procedure.step,
                        stepDescription = procedure.description
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientScreenPreview() {
    val sampleRecipe = Recipe(
        id = 1,
        category = "Indian",
        name = "Spicy chicken burger with French fries",
        image = "https://images.unsplash.com/photo-1598515213692-5f2824124933?q=80&w=2070&auto=format&fit=crop",
        chef = "Laura Wilson",
        time = "20 min",
        rating = 4.0,
        ingredients = listOf(
            RecipeIngredient(Ingredient(1, "Tomatos", ""), 500),
            RecipeIngredient(Ingredient(2, "Cabbage", ""), 300),
            RecipeIngredient(Ingredient(3, "Taco", ""), 300),
            RecipeIngredient(Ingredient(4, "Slice Bread", ""), 300)
        )
    )
    val sampleProcedures = listOf(
        Procedure(1, 1, "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"),
        Procedure(1, 2, "Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"),
        Procedure(1, 3, "Lorem Ipsum tempor incididunt ut labore et dolore, in voluptate velit esse cillum dolore eu fugiat nulla pariatur?")
    )
    IngredientScreen(
        recipe = sampleRecipe,
        procedures = sampleProcedures,
        onBack = {},
        selectedTab = "Procedure",
        onTabSelected = {}
    )
}
