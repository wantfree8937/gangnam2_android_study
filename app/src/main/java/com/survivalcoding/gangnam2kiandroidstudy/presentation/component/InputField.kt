package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .size(width = 315.dp, height = 81.dp)
    ) {
        Text(
            text = label,
            style = AppTextStyles.smallTextRegular,
            color = AppColors.black
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    style = AppTextStyles.smallTextRegular2,
                    color = AppColors.gray4
                )
            },
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.primary80,
                unfocusedBorderColor = AppColors.gray4,
                focusedContainerColor = AppColors.white,
                unfocusedContainerColor = AppColors.white,
                disabledContainerColor = AppColors.gray4,
                disabledTextColor = AppColors.gray2,
                cursorColor = AppColors.primary100,
            ),
            textStyle = AppTextStyles.smallTextRegular2.copy(color = AppColors.black)
        )
    }
}