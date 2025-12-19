package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun MediumButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
) {
    Box(
        modifier = modifier
            .size(width = 243.dp, height = 54.dp)
            .background(
                color = if (enabled) AppColors.primary100 else AppColors.gray4,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(enabled = enabled) {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(width = 114.dp, height = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    style = AppTextStyles.normalTextBold.copy(
                        fontWeight = FontWeight.Bold,
                        color = AppColors.white
                    ),
                )
            }
            Spacer(modifier = Modifier.width(9.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                tint = AppColors.white,
                contentDescription = "오른쪽 화살표"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MediumButtonPreview() {
    Column {
        MediumButton("Enabled")
        Spacer(modifier = Modifier.height(8.dp))
        MediumButton("Disabled", enabled = false)
    }
}