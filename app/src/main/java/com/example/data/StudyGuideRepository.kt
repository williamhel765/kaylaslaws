package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.UserJournalEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

class StudyGuideRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("law_study_guide_prefs", Context.MODE_PRIVATE)

    private val _completedChapters = MutableStateFlow<Set<Int>>(loadCompletedChapters())
    val completedChapters: StateFlow<Set<Int>> = _completedChapters.asStateFlow()

    private val _bookmarkedPrinciples = MutableStateFlow<Set<String>>(loadBookmarkedPrinciples())
    val bookmarkedPrinciples: StateFlow<Set<String>> = _bookmarkedPrinciples.asStateFlow()

    private val _journalEntries = MutableStateFlow<List<UserJournalEntry>>(loadJournalEntries())
    val journalEntries: StateFlow<List<UserJournalEntry>> = _journalEntries.asStateFlow()

    private val _quizHighScore = MutableStateFlow(prefs.getInt("quiz_high_score", 0))
    val quizHighScore: StateFlow<Int> = _quizHighScore.asStateFlow()

    private fun loadCompletedChapters(): Set<Int> {
        val set = prefs.getStringSet("completed_chapters", emptySet()) ?: emptySet()
        return set.mapNotNull { it.toIntOrNull() }.toSet()
    }

    private fun loadBookmarkedPrinciples(): Set<String> {
        return prefs.getStringSet("bookmarked_principles", emptySet()) ?: emptySet()
    }

    private fun loadJournalEntries(): List<UserJournalEntry> {
        val jsonString = prefs.getString("journal_entries_json", null) ?: return emptyList()
        return try {
            val list = mutableListOf<UserJournalEntry>()
            val array = JSONArray(jsonString)
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                list.add(
                    UserJournalEntry(
                        id = obj.getString("id"),
                        chapterId = obj.optInt("chapterId", 1),
                        timestamp = obj.optLong("timestamp", System.currentTimeMillis()),
                        prompt = obj.getString("prompt"),
                        answer = obj.getString("answer")
                    )
                )
            }
            list.sortedByDescending { it.timestamp }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun toggleChapterCompletion(chapterId: Int) {
        val current = _completedChapters.value.toMutableSet()
        if (current.contains(chapterId)) {
            current.remove(chapterId)
        } else {
            current.add(chapterId)
        }
        prefs.edit().putStringSet("completed_chapters", current.map { it.toString() }.toSet()).apply()
        _completedChapters.value = current
    }

    fun togglePrincipleBookmark(principleId: String) {
        val current = _bookmarkedPrinciples.value.toMutableSet()
        if (current.contains(principleId)) {
            current.remove(principleId)
        } else {
            current.add(principleId)
        }
        prefs.edit().putStringSet("bookmarked_principles", current).apply()
        _bookmarkedPrinciples.value = current
    }

    fun saveJournalEntry(chapterId: Int, prompt: String, answer: String) {
        if (answer.isBlank()) return
        val current = _journalEntries.value.toMutableList()
        val newEntry = UserJournalEntry(
            id = "entry_${System.currentTimeMillis()}",
            chapterId = chapterId,
            timestamp = System.currentTimeMillis(),
            prompt = prompt,
            answer = answer.trim()
        )
        current.add(0, newEntry)
        saveJournalList(current)
    }

    fun deleteJournalEntry(entryId: String) {
        val current = _journalEntries.value.toMutableList()
        current.removeAll { it.id == entryId }
        saveJournalList(current)
    }

    private fun saveJournalList(entries: List<UserJournalEntry>) {
        val array = JSONArray()
        entries.forEach { entry ->
            val obj = JSONObject().apply {
                put("id", entry.id)
                put("chapterId", entry.chapterId)
                put("timestamp", entry.timestamp)
                put("prompt", entry.prompt)
                put("answer", entry.answer)
            }
            array.put(obj)
        }
        prefs.edit().putString("journal_entries_json", array.toString()).apply()
        _journalEntries.value = entries
    }

    fun recordQuizScore(score: Int) {
        val currentHigh = _quizHighScore.value
        if (score > currentHigh) {
            prefs.edit().putInt("quiz_high_score", score).apply()
            _quizHighScore.value = score
        }
    }
}
