package com.survivalcoding.gangnam2kiandroidstudy.recipe_provider.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.local.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.local.RecipeEntity
import kotlinx.coroutines.runBlocking

class RecipeProvider : ContentProvider() {

    companion object {
        fun getContentUri(context: android.content.Context): Uri {
            return Uri.parse("content://${context.packageName}.recipe.provider/recipes")
        }

        private const val RECIPES = 1
        private const val RECIPE_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            // Note: uriMatcher requires authorities to be known at initialization.
            // Since authority is dynamic, we'll need to match against the actual authority at runtime or use a wildcard.
            // A common trick is to initialize it in onCreate.
        }
    }

    private lateinit var uriMatcher: UriMatcher

    private lateinit var database: AppDatabase

    override fun onCreate(): Boolean {
        val authority = context!!.packageName + ".recipe.provider"
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(authority, "recipes", RECIPES)
            addURI(authority, "recipes/#", RECIPE_ID)
        }
        database = Room.databaseBuilder(
            context!!,
            AppDatabase::class.java,
            "recipe_db"
        ).build()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val dao = database.recipeDao()
        return when (uriMatcher.match(uri)) {
            RECIPES -> dao.getAllRecipesCursor()
            RECIPE_ID -> {
                val id = uri.lastPathSegment?.toInt() ?: return null
                dao.getRecipeByIdCursor(id)
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        val authority = context!!.packageName + ".recipe.provider"
        return when (uriMatcher.match(uri)) {
            RECIPES -> "vnd.android.cursor.dir/$authority.recipes"
            RECIPE_ID -> "vnd.android.cursor.item/$authority.recipes"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if (uriMatcher.match(uri) != RECIPES) {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
        val name = values?.getAsString("name") ?: return null
        val category = values.getAsString("category") ?: ""
        val image = values.getAsString("image") ?: ""
        val chef = values.getAsString("chef") ?: ""
        val time = values.getAsString("time") ?: ""
        val rating = values.getAsDouble("rating") ?: 0.0

        val id = runBlocking {
            val entity = RecipeEntity(
                id = (System.currentTimeMillis() % Int.MAX_VALUE).toInt(),
                category = category,
                name = name,
                image = image,
                chef = chef,
                time = time,
                rating = rating,
                ingredients = emptyList() // Simple mapping for now
            )
            database.recipeDao().insertRecipe(entity)
            entity.id
        }
        context?.contentResolver?.notifyChange(uri, null)
        val authority = context!!.packageName + ".recipe.provider"
        return Uri.withAppendedPath(Uri.parse("content://$authority/recipes"), id.toString())
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val count = when (uriMatcher.match(uri)) {
            RECIPE_ID -> {
                val id = uri.lastPathSegment?.toInt() ?: return 0
                runBlocking { database.recipeDao().deleteRecipeById(id) }
                1
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        if (count > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }
        return count
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val count = when (uriMatcher.match(uri)) {
            RECIPE_ID -> {
                val id = uri.lastPathSegment?.toInt() ?: return 0
                val name = values?.getAsString("name") ?: return 0
                val category = values.getAsString("category") ?: ""
                runBlocking {
                    val existing = database.recipeDao().getRecipeById(id)
                    if (existing != null) {
                        database.recipeDao().insertRecipe(existing.copy(name = name, category = category))
                    }
                }
                1
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        if (count > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }
        return count
    }
}
