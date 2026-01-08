package com.survivalcoding.gallery.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.survivalcoding.gallery.data.model.Photo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "gallery") {
                        composable("gallery") {
                            GalleryScreen(
                                onPhotoClick = { photo ->
                                    val photoJson = Json.encodeToString(Photo.serializer(), photo)
                                    val encodedPhoto = java.net.URLEncoder.encode(photoJson, StandardCharsets.UTF_8.toString())
                                    navController.navigate("photo_detail/$encodedPhoto")
                                }
                            )
                        }

                        composable(
                            route = "photo_detail/{photo}",
                            arguments = listOf(navArgument("photo") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val photoJson = backStackEntry.arguments?.getString("photo")
                            if (photoJson != null) {
                                val decodedJson = URLDecoder.decode(photoJson, StandardCharsets.UTF_8.toString())
                                val photo = Json.decodeFromString(Photo.serializer(), decodedJson)
                                PhotoDetailScreen(
                                    photo = photo,
                                    onBack = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
