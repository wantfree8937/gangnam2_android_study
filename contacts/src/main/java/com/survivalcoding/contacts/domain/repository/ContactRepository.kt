package com.survivalcoding.contacts.domain.repository

import com.survivalcoding.contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getContacts(): Flow<List<Contact>>
    suspend fun toggleFavorite(contactId: String)
}
