package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.Gangnam2kiAndroidStudyTheme

@Composable
fun Tabs(
    labels: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val horizontalPadding = when (labels.size) {
        2 -> 30.dp
        3 -> 20.dp
        else -> 10.dp
    }
    val arrangementSpace = when (labels.size) {
        2 -> 15.dp
        3 -> 7.dp
        else -> 10.dp
    }

    Row(
        modifier = modifier
            .size(width = 375.dp, height = 58.dp)
            .background(color = AppColors.white)
            .padding(horizontal = horizontalPadding),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(arrangementSpace)
    ) {
        labels.forEachIndexed { index, label ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 12.dp)
                    .height(33.dp)
                    .background(
                        // 선택된 탭은 색상 배경, 선택되지 않은 탭은 투명 배경
                        color = if (selectedTabIndex == index) AppColors.primary100 else Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onTabSelected(index) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    color = if (selectedTabIndex == index) AppColors.white else AppColors.primary80
                )
            }
        }
    }
}
