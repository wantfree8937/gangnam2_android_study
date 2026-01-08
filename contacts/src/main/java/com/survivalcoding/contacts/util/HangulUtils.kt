package com.survivalcoding.contacts.util

object HangulUtils {
    private val HANGUL_INITIAL = charArrayOf(
        'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    )

    fun getInitialConsonants(text: String): String {
        return text.map { char ->
            if (char in '\uAC00'..'\uD7A3') {
                val unicode = char.code - 0xAC00
                val initialIndex = unicode / (21 * 28)
                HANGUL_INITIAL[initialIndex]
            } else {
                char
            }
        }.joinToString("")
    }

    fun containsInitialConsonant(source: String, query: String): Boolean {
        if (query.isEmpty()) return true
        val initials = getInitialConsonants(source)
        return initials.contains(query)
    }
}
