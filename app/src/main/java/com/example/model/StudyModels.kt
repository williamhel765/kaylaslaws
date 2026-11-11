package com.example.model

data class Principle(
    val id: String,
    val title: String,
    val subtitle: String,
    val coreAxiom: String,
    val deepExplanation: String,
    val scientificSpiritualAnalogy: String,
    val practicalApplication: String,
    val journalPrompt: String,
    val dailyAffirmation: String
)

data class StudyChapter(
    val id: Int,
    val partNumber: Int,
    val partTitle: String,
    val chapterTitle: String,
    val summary: String,
    val deepDiveSections: List<SectionContent>,
    val keyTakeaways: List<String>,
    val reflectionQuestions: List<String>,
    val alignmentExercise: AlignmentExercise
)

data class SectionContent(
    val heading: String,
    val body: String
)

data class AlignmentExercise(
    val title: String,
    val goal: String,
    val steps: List<String>,
    val trapToAvoid: String
)

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class UserJournalEntry(
    val id: String,
    val chapterId: Int,
    val timestamp: Long,
    val prompt: String,
    val answer: String
)
