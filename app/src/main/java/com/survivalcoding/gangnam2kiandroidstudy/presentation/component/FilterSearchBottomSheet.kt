package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.Gangnam2kiAndroidStudyTheme

data class FilterSearchState(
    val selectedTime: String? = null,
    val selectedRate: String? = null,
    val selectedCategory: String? = null
)

@Composable
fun FilterSearchBottomSheet(
    modifier: Modifier = Modifier
) {
    var state by remember { mutableStateOf(FilterSearchState()) }

    val timeFilters = listOf("All", "Newest", "Oldest", "Popularity")
    val rateFilters = listOf("5", "4", "3", "2", "1")
    val categoryFilters = listOf(
        "All", "Cereal", "Vegetables", "Dinner", "Chinese",
        "Local Dish", "Fruit", "BreakFast", "Spanish", "Lunch"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Filter Search", style = AppTextStyles.mediumTextBold)
            Spacer(modifier = Modifier.height(24.dp))

            FilterSection(title = "Time") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    timeFilters.forEach {
                        TextFilterButton(
                            text = it,
                            isSelected = state.selectedTime == it,
                            onClick = { state = state.copy(selectedTime = it) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            FilterSection(title = "Rate") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    rateFilters.forEach {
                        RatingButton(
                            rating = it,
                            isSelected = state.selectedRate == it,
                            onClick = { state = state.copy(selectedRate = it) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            FilterSection(title = "Category") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    categoryFilters.chunked(4).forEach { rowItems ->
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            rowItems.forEach {
                                TextFilterButton(
                                    text = it,
                                    isSelected = state.selectedCategory == it,
                                    onClick = { state = state.copy(selectedCategory = it) }
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { /* TODO: Filter action */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppColors.primary100)
        ) {
            Text(text = "Filter", style = AppTextStyles.mediumTextBold)
        }
    }
}

@Composable
fun FilterSection(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(text = title, style = AppTextStyles.mediumTextBold)
        Spacer(modifier = Modifier.height(12.dp))
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun FilterSearchBottomSheetPreview() {
    Gangnam2kiAndroidStudyTheme {
        Surface {
            FilterSearchBottomSheet()
        }
    }
}
