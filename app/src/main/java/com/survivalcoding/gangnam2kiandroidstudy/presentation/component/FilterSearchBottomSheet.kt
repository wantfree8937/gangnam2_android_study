package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterSearchBottomSheet(
    onDismissRequest: () -> Unit,
    onFilter: (FilterSearchState) -> Unit,
    initialState: FilterSearchState,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = null
    ) {
        var filterState by remember { mutableStateOf(initialState) }

        val timeFilters = listOf("All", "Newest", "Oldest", "Popularity")
        val rateFilters = listOf("5", "4", "3", "2", "1")
        val categoryFilters = listOf(
            "All", "Cereal", "Vegetables", "Dinner", "Chinese",
            "Local Dish", "Fruit", "BreakFast", "Spanish", "Lunch"
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(31.dp))
            Text(
                text = "Filter Search",
                modifier = Modifier.fillMaxWidth(),
                style = AppTextStyles.smallTextBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            Column(Modifier.fillMaxWidth()) {
                Text(text = "Time", style = AppTextStyles.normalTextBold)
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    timeFilters.forEach { 
                        SmallButton(
                            text = it,
                            onClick = {
                                filterState = filterState.copy(selectedTime = if (filterState.selectedTime == it) null else it)
                            },
                            isSelected = filterState.selectedTime == it
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(Modifier.fillMaxWidth()) {
                Text(text = "Rate", style = AppTextStyles.normalTextBold)
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    rateFilters.forEach { 
                        SmallButton(
                            text = "$it Star",
                            onClick = {
                                filterState = filterState.copy(selectedRate = if (filterState.selectedRate == it) null else it)
                            },
                            isSelected = filterState.selectedRate == it
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(Modifier.fillMaxWidth()) {
                Text(text = "Category", style = AppTextStyles.normalTextBold)
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    categoryFilters.forEach { 
                        SmallButton(
                            text = it,
                            onClick = {
                                filterState = filterState.copy(selectedCategory = if (filterState.selectedCategory == it) null else it)
                            },
                            isSelected = filterState.selectedCategory == it
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                SmallButton(
                    text = "Filter",
                    onClick = {
                        onFilter(filterState)
                        onDismissRequest()
                    },
                    isSelected = true // Always selected
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}
