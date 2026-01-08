package com.survivalcoding.contacts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.contacts.domain.model.Contact
import com.survivalcoding.contacts.domain.repository.ContactRepository
import com.survivalcoding.contacts.util.HangulUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val searchQuery: String = "",
    val showOnlyFavorites: Boolean = false,
    val isLoading: Boolean = false
)

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ContactState(isLoading = true))
    val state = _state.asStateFlow()

    private val _allContacts = MutableStateFlow<List<Contact>>(emptyList())

    fun fetchContacts() {
        viewModelScope.launch {
            repository.getContacts().collect { list ->
                _allContacts.value = list
                updateFilteredList()
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _state.update { it.copy(searchQuery = query) }
        updateFilteredList()
    }

    fun onToggleFavoriteFilter() {
        _state.update { it.copy(showOnlyFavorites = !it.showOnlyFavorites) }
        updateFilteredList()
    }

    fun onToggleFavorite(contactId: String) {
        viewModelScope.launch {
            repository.toggleFavorite(contactId)
        }
    }

    private fun updateFilteredList() {
        val currentAll = _allContacts.value
        val query = _state.value.searchQuery
        val favoritesOnly = _state.value.showOnlyFavorites

        val filtered = currentAll.filter { contact ->
            val matchesSearch = if (isConsonantQuery(query)) {
                HangulUtils.containsInitialConsonant(contact.name, query)
            } else {
                contact.name.contains(query, ignoreCase = true)
            }
            
            val matchesFilter = if (favoritesOnly) contact.isFavorite else true
            
            matchesSearch && matchesFilter
        }

        _state.update { it.copy(contacts = filtered, isLoading = false) }
    }

    private fun isConsonantQuery(query: String): Boolean {
        return query.all { it in 'ㄱ'..'ㅎ' }
    }
}
