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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Recipe

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe
) {
    Box(
        modifier = modifier
            .size(width = 350.dp, height = 250.dp)
            .clip(RoundedCornerShape(24.dp))
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
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 400f
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(color = Color(0xFFF4BE65), shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "별점",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = recipe.rating.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }

            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = recipe.name,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "By ${recipe.chef}",
                        color = Color.White,
                        fontSize = 14.sp
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.timer),
                            contentDescription = "조리 시간",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = recipe.time,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.inactive),
                            contentDescription = "북마크",
                            modifier = Modifier.size(32.dp)
                        )
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
