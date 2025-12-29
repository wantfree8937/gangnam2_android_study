package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import android.net.Uri
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route

object DeepLinkParser {
    fun parse(uri: Uri?): Route? {
        if (uri?.scheme != "myapp" || uri.host != "recipes") return null

        return when {
            uri.path == "/saved" -> Route.Main(initialTab = Route.SavedRecipes)
            uri.path?.startsWith("/detail/") == true -> {
                val id = uri.lastPathSegment?.toIntOrNull() ?: 0
                Route.RecipeDetail(id)
            }
            else -> null
        }
    }
}
