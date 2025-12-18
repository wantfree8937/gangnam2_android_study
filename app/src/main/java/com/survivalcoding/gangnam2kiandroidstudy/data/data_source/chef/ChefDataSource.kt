package com.survivalcoding.gangnam2kiandroidstudy.data.data_source.chef

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.chef.Chef

interface ChefDataSource {
    suspend fun getChefs(): List<Chef>
}