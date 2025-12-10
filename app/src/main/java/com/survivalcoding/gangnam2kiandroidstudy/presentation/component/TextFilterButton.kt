package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.Gangnam2kiAndroidStudyTheme

@Composable
fun TextFilterButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) AppColors.primary100 else Color.White
    val contentColor = if (isSelected) Color.White else AppColors.primary80

    val borderModifier = if (isSelected) {
        Modifier
    } else {
        Modifier.border(1.dp, AppColors.primary80, RoundedCornerShape(10.dp))
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .then(borderModifier)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = contentColor,
            style = AppTextStyles.smallTextRegular2
        )
    }
}

@Preview(showBackground = true, group = "Buttons")
@Composable
fun TextFilterButtonPreview() {
    Gangnam2kiAndroidStudyTheme {
        Row(modifier = Modifier.padding(16.dp)) {
            TextFilterButton(text = "All", isSelected = false, onClick = {})
            Spacer(modifier = Modifier.width(8.dp))
            TextFilterButton(text = "Food", isSelected = true, onClick = {})
        }
    }
}
