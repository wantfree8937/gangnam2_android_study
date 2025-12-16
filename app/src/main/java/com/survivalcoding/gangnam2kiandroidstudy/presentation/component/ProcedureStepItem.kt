package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun ProcedureStepItem(
    stepNumber: Int,
    stepDescription: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.gray4)
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        Text(
            text = "Step $stepNumber",
            style = AppTextStyles.normalTextBold,
            color = AppColors.black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stepDescription,
            style = AppTextStyles.normalTextRegular,
            color = AppColors.gray3
        )
    }
}

@Preview
@Composable
private fun ProcedureStepItemPreview() {
    ProcedureStepItem(
        stepNumber = 1,
        stepDescription = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
    )
}