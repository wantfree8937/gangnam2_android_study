package com.survivalcoding.gallery.data.source

import android.content.ContentResolver
import android.content.ContentUris
import android.provider.MediaStore
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.gallery.data.model.Photo

/**
 * MediaStore에서 사진을 페이지 단위로 로드하는 PagingSource
 * @property contentResolver ContentResolver instance
 */
class PhotoPagingSource(
    private val contentResolver: ContentResolver
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: 0
        val loadSize = params.loadSize

        return try {
            val photos = queryPhotos(limit = loadSize, offset = position)

            val nextKey = if (photos.size < loadSize) null else position + loadSize
            val prevKey = if (position == 0) null else position - loadSize

            LoadResult.Page(
                data = photos,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
        }
    }

    private fun queryPhotos(limit: Int, offset: Int): List<Photo> {
        val photos = mutableListOf<Photo>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.LATITUDE,
            MediaStore.Images.Media.LONGITUDE
        )

        val cursor = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val bundle = android.os.Bundle().apply {
                putInt(ContentResolver.QUERY_ARG_LIMIT, limit)
                putInt(ContentResolver.QUERY_ARG_OFFSET, offset)
                putStringArray(
                    ContentResolver.QUERY_ARG_SORT_COLUMNS,
                    arrayOf(MediaStore.Images.Media.DATE_TAKEN)
                )
                putInt(
                    ContentResolver.QUERY_ARG_SORT_DIRECTION,
                    ContentResolver.QUERY_SORT_DIRECTION_DESCENDING
                )
            }
            contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                bundle,
                null
            )
        } else {
            val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC LIMIT $limit OFFSET $offset"
            contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
            )
        }

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateTakenColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val latColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.LATITUDE)
            val lngColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.LONGITUDE)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val name = it.getString(nameColumn) ?: "Unknown"
                val dateTaken = it.getLong(dateTakenColumn)
                
                val lat = it.getDouble(latColumn).takeIf { latVal -> latVal != 0.0 }
                val lng = it.getDouble(lngColumn).takeIf { lngVal -> lngVal != 0.0 }

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                photos.add(
                    Photo(
                        id = id,
                        uri = contentUri,
                        name = name,
                        dateTaken = dateTaken,
                        latitude = lat,
                        longitude = lng
                    )
                )
            }
        }
        return photos
    }
}
