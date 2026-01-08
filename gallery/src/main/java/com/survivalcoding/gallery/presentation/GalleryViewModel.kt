package com.survivalcoding.gallery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.survivalcoding.gallery.data.model.Photo
import com.survivalcoding.gallery.data.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    // PagingData Flow를 ViewModel 스코프에 캐시하여 화면 회전 시에도 데이터 유지
    val photos: Flow<PagingData<Photo>> = photoRepository.getPhotos()
        .cachedIn(viewModelScope)
}
