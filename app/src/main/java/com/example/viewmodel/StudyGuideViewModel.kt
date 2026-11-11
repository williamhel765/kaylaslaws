package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.StudyGuideData
import com.example.data.StudyGuideRepository
import com.example.model.Principle
import com.example.model.QuizQuestion
import com.example.model.StudyChapter
import com.example.model.UserJournalEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

enum class ScreenTab {
    OVERVIEW,
    CHAPTERS,
    PRINCIPLES,
    PRACTICE,
    JOURNAL,
    QUIZ
}

data class QuizState(
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val score: Int = 0,
    val isQuizFinished: Boolean = false
)

class StudyGuideViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = StudyGuideRepository(application)

    private val _currentTab = MutableStateFlow(ScreenTab.OVERVIEW)
    val currentTab: StateFlow<ScreenTab> = _currentTab.asStateFlow()

    private val _selectedChapterId = MutableStateFlow<Int?>(null)
    val selectedChapterId: StateFlow<Int?> = _selectedChapterId.asStateFlow()

    private val _selectedPrincipleId = MutableStateFlow<String?>(null)
    val selectedPrincipleId: StateFlow<String?> = _selectedPrincipleId.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val completedChapters: StateFlow<Set<Int>> = repository.completedChapters
    val bookmarkedPrinciples: StateFlow<Set<String>> = repository.bookmarkedPrinciples
    val journalEntries: StateFlow<List<UserJournalEntry>> = repository.journalEntries
    val quizHighScore: StateFlow<Int> = repository.quizHighScore

    val allChapters: List<StudyChapter> = StudyGuideData.chapters
    val allPrinciples: List<Principle> = StudyGuideData.universalPrinciples
    val quizQuestions: List<QuizQuestion> = StudyGuideData.quizQuestions

    private val _quizState = MutableStateFlow(QuizState())
    val quizState: StateFlow<QuizState> = _quizState.asStateFlow()

    val filteredChapters: StateFlow<List<StudyChapter>> = combine(
        _searchQuery
    ) { (query) ->
        if (query.isBlank()) {
            allChapters
        } else {
            allChapters.filter { ch ->
                ch.chapterTitle.contains(query, ignoreCase = true) ||
                ch.summary.contains(query, ignoreCase = true) ||
                ch.partTitle.contains(query, ignoreCase = true) ||
                ch.deepDiveSections.any { it.heading.contains(query, ignoreCase = true) || it.body.contains(query, ignoreCase = true) }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), allChapters)

    val filteredPrinciples: StateFlow<List<Principle>> = combine(
        _searchQuery
    ) { (query) ->
        if (query.isBlank()) {
            allPrinciples
        } else {
            allPrinciples.filter { p ->
                p.title.contains(query, ignoreCase = true) ||
                p.subtitle.contains(query, ignoreCase = true) ||
                p.coreAxiom.contains(query, ignoreCase = true) ||
                p.deepExplanation.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), allPrinciples)

    fun setTab(tab: ScreenTab) {
        _currentTab.value = tab
        // Reset subscreen selections when changing primary tabs
        _selectedChapterId.value = null
        _selectedPrincipleId.value = null
    }

    fun selectChapter(chapterId: Int?) {
        _selectedChapterId.value = chapterId
    }

    fun selectPrinciple(principleId: String?) {
        _selectedPrincipleId.value = principleId
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleChapterCompletion(chapterId: Int) {
        repository.toggleChapterCompletion(chapterId)
    }

    fun togglePrincipleBookmark(principleId: String) {
        repository.togglePrincipleBookmark(principleId)
    }

    fun saveJournalEntry(chapterId: Int, prompt: String, text: String) {
        repository.saveJournalEntry(chapterId, prompt, text)
    }

    fun deleteJournalEntry(entryId: String) {
        repository.deleteJournalEntry(entryId)
    }

    // Quiz functions
    fun selectQuizOption(index: Int) {
        if (!_quizState.value.isAnswerSubmitted) {
            _quizState.value = _quizState.value.copy(selectedOptionIndex = index)
        }
    }

    fun submitQuizAnswer() {
        val state = _quizState.value
        val selected = state.selectedOptionIndex ?: return
        val currentQ = quizQuestions[state.currentQuestionIndex]
        val isCorrect = selected == currentQ.correctIndex
        val newScore = if (isCorrect) state.score + 1 else state.score

        _quizState.value = state.copy(
            isAnswerSubmitted = true,
            score = newScore
        )
    }

    fun nextQuizQuestion() {
        val state = _quizState.value
        if (state.currentQuestionIndex < quizQuestions.size - 1) {
            _quizState.value = state.copy(
                currentQuestionIndex = state.currentQuestionIndex + 1,
                selectedOptionIndex = null,
                isAnswerSubmitted = false
            )
        } else {
            _quizState.value = state.copy(isQuizFinished = true)
            repository.recordQuizScore(_quizState.value.score)
        }
    }

    fun restartQuiz() {
        _quizState.value = QuizState()
    }

    fun calculateProgressPercentage(): Int {
        val completedCount = completedChapters.value.size
        if (allChapters.isEmpty()) return 0
        return ((completedCount.toFloat() / allChapters.size) * 100).toInt()
    }
}
