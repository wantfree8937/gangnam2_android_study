package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RatingRecipe(
    title: String,
    onDismissRequest: () -> Unit,
    onSend: (Int) -> Unit,
    sendButtonText: String = "Send"
) {
    var rating by rememberSaveable { mutableStateOf(0) }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = title,
                    style = AppTextStyles.smallTextRegular2,
                    color = AppColors.black
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (i in 1..5) {
                        Star(
                            selected = i <= rating,
                            onClick = { 
                                rating = if (rating == i) 0 else i
                            }
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .width(61.dp)
                        .height(20.dp)
                        .background(
                            color = if (rating > 0) AppColors.rating else AppColors.gray4,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable(rating > 0) { onSend(rating) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = sendButtonText,
                        style = AppTextStyles.smallerTextSmallLable,
                        color = AppColors.white
                    )
                }
            }
        }
    }
}

@Composable
fun Star(
    selected: Boolean,
    onClick: () -> Unit
) {
    Icon(
        painter = painterResource(id = if (selected) R.drawable.star_2 else R.drawable.star_1),
        contentDescription = "Star",
        modifier = Modifier
            .size(24.dp)
            .clickable { onClick() },
        tint = Color.Unspecified
    )
}
