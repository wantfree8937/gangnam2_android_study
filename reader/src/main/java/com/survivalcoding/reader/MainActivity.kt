package com.survivalcoding.reader

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    // Default authority (change suffix depending on flavor: .dev, .staging, or empty)
    private var authority by mutableStateOf("com.survivalcoding.gangnam2kiandroidstudy.dev.recipe.provider")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var recipes by remember { mutableStateOf(listOf<Recipe>()) }
            val scope = rememberCoroutineScope()

            fun fetchRecipes() {
                val contentUri = Uri.parse("content://$authority/recipes")
                scope.launch {
                    val list = withContext(Dispatchers.IO) {
                        val result = mutableListOf<Recipe>()
                        try {
                            val cursor: Cursor? = contentResolver.query(contentUri, null, null, null, null)
                            cursor?.use {
                                val nameIndex = it.getColumnIndex("name")
                                val ingredientsIndex = it.getColumnIndex("ingredients")
                                
                                while (it.moveToNext()) {
                                    val name = it.getString(nameIndex)
                                    val ingredients = it.getString(ingredientsIndex)
                                    result.add(Recipe(name, ingredients))
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        result
                    }
                    recipes = list
                }
            }

            LaunchedEffect(authority) {
                fetchRecipes()
            }

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Recipe Reader App", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                
                TextField(
                    value = authority,
                    onValueChange = { authority = it },
                    label = { Text("Provider Authority") },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Flavor suffixes: .dev, .staging, or none", style = MaterialTheme.typography.bodySmall)
                
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { fetchRecipes() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Refresh Data")
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(recipes) { recipe ->
                        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(recipe.name, style = MaterialTheme.typography.titleMedium)
                                Text("Ingredients: ${recipe.ingredients}")
                            }
                        }
                    }
                }
            }
        }
    }
}

data class Recipe(val name: String, val ingredients: String)
