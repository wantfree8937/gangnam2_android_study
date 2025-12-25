package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository

class FakeClipboardRepository : ClipboardRepository {
    var lastCopiedText: String? = null
        private set

    override fun copyText(text: String) {
        lastCopiedText = text
    }
}
