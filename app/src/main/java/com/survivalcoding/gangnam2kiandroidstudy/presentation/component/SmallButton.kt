package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean = false
) {
    val colors = if (isSelected) {
        ButtonDefaults.buttonColors(containerColor = AppColors.primary100, contentColor = AppColors.white)
    } else {
        ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = AppColors.primary80)
    }

    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = colors,
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 5.dp)
    ) {
        Text(
            text = text,
            style = AppTextStyles.smallerTextBold
        )
    }
}
