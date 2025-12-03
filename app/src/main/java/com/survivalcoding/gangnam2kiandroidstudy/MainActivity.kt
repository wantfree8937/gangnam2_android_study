package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tabs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComponentShow()
        }
    }
}

@Composable
fun ComponentShow() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp) // 컴포넌트 사이 간격
    ) {
        // Buttons
        BigButton(text = "Button")
        MediumButton(text = "Button")
        SmallButton(text = "Button")

        Spacer(modifier = Modifier.height(15.dp))

        // InputField States
        InputField(
            value = "",
            onValueChange = {},
            label = "Label",
            placeholder = "Placeholder",
        )

        InputField(
            value = "",
            onValueChange = {},
            label = "Label",
            placeholder = "Placeholder",
        )

        InputField(
            value = "Placeholder",
            onValueChange = {},
            label = "Label",
            placeholder = "Placeholder",
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs States
        Tabs(labels = listOf("label", "label"), selectedTabIndex = 0, onTabSelected = { })
        Tabs(labels = listOf("label", "label"), selectedTabIndex = 1, onTabSelected = { })
    }
}