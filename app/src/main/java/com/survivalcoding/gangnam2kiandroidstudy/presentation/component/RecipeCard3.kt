package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeCard3(
    modifier: Modifier = Modifier,
    recipeName: String,
    rating: Double,
    chefImageUrl: String,
    chefName: String,
    time: String,
    recipeImageUrl: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .width(251.dp)
            .height(140.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(105.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = onClick
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.fillMaxWidth(0.65f)) {
                        Text(
                            text = recipeName,
                            style = AppTextStyles.smallTextBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(5) { index ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = if (index < rating.toInt()) AppColors.secondary100 else AppColors.gray2,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(
                                model = chefImageUrl,
                                contentDescription = "chef image",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "By $chefName",
                                style = AppTextStyles.smallTextRegular2,
                                color = AppColors.gray3
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.timer),
                                contentDescription = "time",
                                modifier = Modifier.size(16.dp),
                                tint = AppColors.gray3
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = time,
                                style = AppTextStyles.smallTextRegular2,
                                color = AppColors.gray3
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(45.dp))
            }
        }

        AsyncImage(
            model = recipeImageUrl,
            contentDescription = "recipe image",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 9.dp)
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeCard3Preview() {
    Box(modifier = Modifier.padding(top = 40.dp)) {
        RecipeCard3(
            recipeName = "Steak with tomato...",
            rating = 4.0,
            chefImageUrl = "",
            chefName = "James Milner",
            time = "20 mins",
            recipeImageUrl = ""
        )
    }
}
