package com.survivalcoding.gallery.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.survivalcoding.gallery.data.model.Photo
import com.survivalcoding.gallery.data.source.PhotoPagingSource
import kotlinx.coroutines.flow.Flow

/**
 * 갤러리 데이터에 접근하는 Repository
 * @property context Application Context needed for ContentResolver
 */
class PhotoRepository(private val context: Context) {

    /**
     * 사진 데이터를 PagingData Flow로 반환
     */
    fun getPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30, // 한 번에 불러올 개수
                enablePlaceholders = false,
                initialLoadSize = 30
            ),
            pagingSourceFactory = { PhotoPagingSource(context.contentResolver) }
        ).flow
    }
}
