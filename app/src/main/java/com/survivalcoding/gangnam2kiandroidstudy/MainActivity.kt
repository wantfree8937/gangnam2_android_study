package com.survivalcoding.gangnam2kiandroidstudy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.NavigationRoot
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var deepLinkRoute by mutableStateOf<Route?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deepLinkRoute = handleIntent(intent)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                NavigationRoot(deepLinkDest = deepLinkRoute)
            }
        }
    }

    public override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        deepLinkRoute = handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?): Route? {
        return com.survivalcoding.gangnam2kiandroidstudy.core.routing.DeepLinkParser.parse(intent?.data)
    }
}
