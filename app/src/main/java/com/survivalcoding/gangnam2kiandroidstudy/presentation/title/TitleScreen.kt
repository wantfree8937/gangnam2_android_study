package com.survivalcoding.gangnam2kiandroidstudy.presentation.title

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun TitleScreen(
    onClickSignIn: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.title),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.4f),
                            Color.Black
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(104.dp))
            Column(
                modifier = Modifier.padding(horizontal = 83.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chief),
                    contentDescription = "chief hat",
                    modifier = Modifier.size(79.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "100K+ Premium Recipe",
                    color = Color.White,
                    style = AppTextStyles.mediumTextBold
                )
            }
            Spacer(modifier = Modifier.height(222.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Get\nCooking",
                    color = Color.White,
                    style = AppTextStyles.titleTextBold,
                    textAlign = TextAlign.Center,
                    lineHeight = 60.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Simple way to find Tasty Recipe",
                    color = Color.White,
                    style = AppTextStyles.normalTextRegular
                )
            }
            Spacer(modifier = Modifier.height(64.dp))
            MediumButton(
                text = "Start Cooking",
                onClick = { onClickSignIn() }
            )
        }
    }
}
