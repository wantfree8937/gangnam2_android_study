package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeCard2(
    modifier: Modifier = Modifier,
    recipeName: String,
    time: String,
    rating: Double,
    imageUrl: String,
    onCardClick: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(150.dp)
            .height(231.dp)
            .clickable { onCardClick() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 60.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = recipeName,
                    style = AppTextStyles.smallTextBold,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Time",
                            style = AppTextStyles.smallTextRegular2,
                            color = AppColors.gray3
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = time,
                            style = AppTextStyles.smallerTextBold,
                            color = AppColors.gray1
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(AppColors.white)
                            .clickable { onBookmarkClick() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.inactive),
                            contentDescription = "Bookmark",
                            tint = AppColors.gray3,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        AsyncImage(
            model = imageUrl,
            placeholder = painterResource(id = R.drawable.title),
            error = painterResource(id = R.drawable.title),
            contentDescription = recipeName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(110.dp)
                .clip(CircleShape)
                .shadow(elevation = 8.dp, shape = CircleShape)
        )

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 30.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(AppColors.secondary20)
                .padding(horizontal = 7.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star_2),
                contentDescription = "Rating",
                modifier = Modifier.size(10.dp),
                tint = AppColors.rating
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = rating.toString(),
                style = AppTextStyles.smallTextRegular2,
                color = AppColors.black
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun RecipeCard2Preview() {
    RecipeCard2(
        recipeName = "Crunchy Nut Coleslaw",
        time = "10 Mins",
        rating = 3.5,
        imageUrl = "",
        onCardClick = {}, // Preview에서는 비워둡니다.
        onBookmarkClick = {} // Preview에서는 비워둡니다.
    )
}
