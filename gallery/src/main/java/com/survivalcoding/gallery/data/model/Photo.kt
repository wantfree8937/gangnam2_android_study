package com.survivalcoding.gallery.data.model

import android.net.Uri
import kotlinx.serialization.Serializable
import com.survivalcoding.gallery.core.serializers.UriSerializer

/**
 * 갤러리에서 보여줄 사진 정보를 담는 데이터 클래스
 * @property id MediaStore ID
 * @property uri 사진의 Content URI
 * @property name 파일 이름
 * @property dateTaken 찍은 날짜 (timestamp)
 * @property latitude 위도 (없으면 null)
 * @property longitude 경도 (없으면 null)
 */
@Serializable
data class Photo(
    val id: Long,
    @Serializable(with = UriSerializer::class)
    val uri: Uri,
    val name: String,
    val dateTaken: Long,
    val latitude: Double? = null,
    val longitude: Double? = null
) : java.io.Serializable
