package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun BottomNavigationBar(
    currentKey: NavKey,
    onTabSelected: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color.White)
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top
    ) {
        IconButton(onClick = { onTabSelected(Route.Home) }) {
            Icon(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "Home",
                modifier = Modifier.size(24.dp),
                tint = if (currentKey == Route.Home) AppColors.primary100 else AppColors.gray4
            )
        }
        IconButton(onClick = { onTabSelected(Route.SavedRecipes) }) {
            Icon(
                painter = painterResource(id = R.drawable.inactive),
                contentDescription = "Saved",
                modifier = Modifier.size(24.dp),
                tint = if (currentKey == Route.SavedRecipes) AppColors.primary100 else AppColors.gray4
            )
        }
        IconButton(onClick = { /* TODO: Handle navigation to Notifications */ }) {
            Icon(
                painter = painterResource(id = R.drawable.notification_bing),
                contentDescription = "Notifications",
                modifier = Modifier.size(24.dp),
                tint = AppColors.gray4
            )
        }
        IconButton(onClick = { /* TODO: Handle navigation to Profile */ }) {
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier.size(24.dp),
                tint = AppColors.gray4
            )
        }
    }
}