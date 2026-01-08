package com.survivalcoding.contacts.data.repository

import android.content.ContentResolver
import android.provider.ContactsContract
import com.survivalcoding.contacts.data.local.FavoriteContactEntity
import com.survivalcoding.contacts.data.local.FavoriteDao
import com.survivalcoding.contacts.domain.model.Contact
import com.survivalcoding.contacts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    private val favoriteDao: FavoriteDao
) : ContactRepository {

    override fun getContacts(): Flow<List<Contact>> {
        val contactsFlow = flow {
            val contactList = mutableListOf<Contact>()
            try {
                val cursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrayOf(
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Phone.PHOTO_URI
                    ),
                    null,
                    null,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
                )

                cursor?.use {
                    val idIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
                    val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                    val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    val photoIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)

                    while (it.moveToNext()) {
                        val id = it.getString(idIndex)
                        val name = it.getString(nameIndex)
                        val number = it.getString(numberIndex)
                        val photo = it.getString(photoIndex)

                        contactList.add(Contact(id, name, number, photo))
                    }
                }
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
            emit(contactList)
        }

        return combine(contactsFlow, favoriteDao.getAllFavoriteIds()) { contacts, favoriteIds ->
            contacts.map { contact ->
                contact.copy(isFavorite = favoriteIds.contains(contact.id))
            }
        }
    }

    override suspend fun toggleFavorite(contactId: String) {
        if (favoriteDao.isFavorite(contactId)) {
            favoriteDao.deleteFavorite(FavoriteContactEntity(contactId))
        } else {
            favoriteDao.insertFavorite(FavoriteContactEntity(contactId))
        }
    }
}
