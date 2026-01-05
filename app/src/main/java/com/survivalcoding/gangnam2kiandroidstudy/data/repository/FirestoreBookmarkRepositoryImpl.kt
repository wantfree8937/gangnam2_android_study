package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreBookmarkRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : BookmarkRepository {

    private val currentUser get() = auth.currentUser

    private fun getCollectionRef() = currentUser?.let { user ->
        firestore.collection("users").document(user.uid).collection("bookmarks")
    }

    override suspend fun getBookmarks(): List<Recipe> {
        return try {
            val collection = getCollectionRef() ?: throw IllegalStateException("User not logged in")
            val snapshot = collection.get().await()
            snapshot.documents.mapNotNull { doc ->
                doc.toObject(RecipeDto::class.java)?.toDomain()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun getBookmarksFlow(): Flow<List<Recipe>> = callbackFlow {
        val user = currentUser
        if (user == null) {
            Log.d("FirestoreRepo", "User not logged in, sending empty list")
            trySend(emptyList())
            close()
            return@callbackFlow
        }

        val collection = firestore.collection("users").document(user.uid).collection("bookmarks")
        Log.d("FirestoreRepo", "Starting snapshot listener for user: ${user.uid}")

        val listener = collection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("FirestoreRepo", "Listen failed", error)
                close(error)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                Log.d("FirestoreRepo", "Snapshot received. Documents: ${snapshot.size()}")
                val recipes = snapshot.documents.mapNotNull { doc ->
                    doc.toObject(RecipeDto::class.java)?.toDomain()
                }
                trySend(recipes)
            }
        }

        awaitClose {
            Log.d("FirestoreRepo", "Stopping snapshot listener")
            listener.remove()
        }
    }

    override suspend fun addBookmark(recipe: Recipe) {
        try {
            val collection = getCollectionRef() ?: throw IllegalStateException("User not logged in")
            val dto = RecipeDto.fromDomain(recipe)
            Log.d("FirestoreRepo", "Adding bookmark: ${recipe.id}")
            collection.document(recipe.id.toString()).set(dto, SetOptions.merge()).await()
            Log.d("FirestoreRepo", "Bookmark added successfully")
        } catch (e: Exception) {
            Log.e("FirestoreRepo", "Error adding bookmark", e)
            throw e
        }
    }

    override suspend fun removeBookmark(recipe: Recipe) {
        try {
            val collection = getCollectionRef() ?: throw IllegalStateException("User not logged in")
            Log.d("FirestoreRepo", "Removing bookmark: ${recipe.id}")
            collection.document(recipe.id.toString()).delete().await()
            Log.d("FirestoreRepo", "Bookmark removed successfully")
        } catch (e: Exception) {
            Log.e("FirestoreRepo", "Error removing bookmark", e)
            throw e
        }
    }

    override suspend fun isBookmarked(id: Int): Boolean {
        return try {
            val collection = getCollectionRef() ?: throw IllegalStateException("User not logged in")
            val doc = collection.document(id.toString()).get().await()
            doc.exists()
        } catch (e: Exception) {
            Log.e("FirestoreRepo", "Error checking bookmark", e)
            throw e
        }
    }

    // Firestore 저장을 위한 DTO
    data class RecipeDto(
        val id: Int = 0,
        val category: String = "",
        val name: String = "",
        val image: String = "",
        val chef: String = "",
        val time: String = "",
        val rating: Double = 0.0,
        val ingredients: List<Map<String, Any>> = emptyList()
    ) {
        fun toDomain(): Recipe {
            return Recipe(
                id = id,
                category = category,
                name = name,
                image = image,
                chef = chef,
                time = time,
                rating = rating,
                ingredients = ingredients.map { map ->
                    RecipeIngredient(
                        ingredient = Ingredient(
                            id = (map["ingredient_id"] as? Long)?.toInt() ?: 0,
                            name = map["ingredient_name"] as? String ?: "",
                            image = map["ingredient_image"] as? String ?: ""
                        ),
                        amount = (map["amount"] as? Long)?.toInt() ?: 0
                    )
                }
            )
        }

        companion object {
            fun fromDomain(recipe: Recipe): RecipeDto {
                return RecipeDto(
                    id = recipe.id,
                    category = recipe.category,
                    name = recipe.name,
                    image = recipe.image,
                    chef = recipe.chef,
                    time = recipe.time,
                    rating = recipe.rating,
                    ingredients = recipe.ingredients.map {
                        mapOf(
                            "ingredient_id" to it.ingredient.id,
                            "ingredient_name" to it.ingredient.name,
                            "ingredient_image" to it.ingredient.image,
                            "amount" to it.amount
                        )
                    }
                )
            }
        }
    }
}