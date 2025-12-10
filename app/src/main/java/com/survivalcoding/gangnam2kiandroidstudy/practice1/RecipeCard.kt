package com.survivalcoding.gangnam2kiandroidstudy.practice1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    showDetails: Boolean = true
) {
    Box(
        modifier = modifier
            .size(width = 315.dp, height = 150.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        AsyncImage(
            model = recipe.image,
            contentDescription = recipe.name,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(color = AppColors.secondary20, shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 7.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "별점",
                    tint = AppColors.rating,
                    modifier = Modifier.size(8.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = recipe.rating.toString(),
                    color = AppColors.black,
                    style = AppTextStyles.smallerTextSmallLable
                )
            }

            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    modifier = Modifier.widthIn(max = 157.dp),
                    text = recipe.name,
                    color = Color.White,
                    style = AppTextStyles.smallerTextBold,
                    lineHeight = 20.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "By ${recipe.chef}",
                        color = Color.White,
                        style = AppTextStyles.smallerTextSmallLable
                    )

                    if (showDetails) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.timer),
                                    contentDescription = "조리 시간",
                                    tint = Color.White,
                                    modifier = Modifier.size(17.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = recipe.time,
                                    color = Color.White,
                                    style = AppTextStyles.smallTextRegular2
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(color = AppColors.white),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.inactive),
                                    contentDescription = "북마크",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    val sampleRecipe = Recipe(
        id = 1,
        category = "Indian",
        name = "Traditional spare ribs\nbaked",
        image = "https://images.unsplash.com/photo-1598515213692-5f2824124933?q=80&w=2070&auto=format&fit=crop",
        chef = "Chef John",
        time = "20 min",
        rating = 4.0,
        ingredients = emptyList()
    )
    Surface(color = Color.DarkGray) {
        RecipeCard(
            modifier = Modifier.padding(16.dp),
            recipe = sampleRecipe
        )
    }
}
