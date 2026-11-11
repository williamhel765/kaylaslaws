package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.StudyGuideData
import com.example.model.StudyChapter
import com.example.ui.components.ChapterCard
import com.example.ui.components.ChapterPracticeQuizModule
import com.example.ui.components.ExerciseCard
import com.example.ui.theme.CelestialCyan
import com.example.ui.theme.CosmicNavyBorder
import com.example.ui.theme.CosmicNavyCardElevated
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.TextMuted
import com.example.viewmodel.StudyGuideViewModel

@Composable
fun ChaptersListContent(
    viewModel: StudyGuideViewModel,
    onChapterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val chapters by viewModel.filteredChapters.collectAsState()
    val completedChapters by viewModel.completedChapters.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "Chapters of The Law Behind The Laws",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Select any chapter to explore its summary, deep dive, and core universal principles",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextMuted
                )
            }
        }

        // Search Bar
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                placeholder = { Text("Search chapters, terms, principles...", color = TextMuted) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = GoldPrimary
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("chapter_search_input"),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GoldPrimary,
                    unfocusedBorderColor = CosmicNavyBorder,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                ),
                singleLine = true
            )
        }

        items(chapters, key = { it.id }) { chapter ->
            ChapterCard(
                chapter = chapter,
                isCompleted = completedChapters.contains(chapter.id),
                onToggleComplete = { viewModel.toggleChapterCompletion(chapter.id) },
                onClick = { onChapterClick(chapter.id) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ChapterDetailView(
    chapter: StudyChapter,
    isCompleted: Boolean,
    onToggleComplete: () -> Unit,
    onBack: () -> Unit,
    onSaveJournal: (String, String) -> Unit,
    onPrincipleClick: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var journalText by remember { mutableStateOf("") }
    var selectedPrompt by remember { mutableStateOf(chapter.reflectionQuestions.firstOrNull() ?: "") }
    var journalSavedFeedback by remember { mutableStateOf(false) }

    // Find the primary associated principle for this chapter
    val associatedPrinciple = remember(chapter.id) {
        StudyGuideData.universalPrinciples.getOrNull(chapter.id - 1)
    }

    val chapterQuizQuestion = remember(chapter.id) {
        StudyGuideData.quizQuestions.find { it.id == chapter.id }
            ?: StudyGuideData.quizQuestions.first()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Back Bar
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("btn_back_chapter")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to Chapters",
                            tint = GoldPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "CHAPTER ${chapter.id}",
                        style = MaterialTheme.typography.labelLarge,
                        color = GoldPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onToggleComplete,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isCompleted) GoldDark else CosmicNavyCardElevated,
                        contentColor = if (isCompleted) Color.White else TextMuted
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.testTag("btn_mark_complete_detail")
                ) {
                    Icon(
                        imageVector = if (isCompleted) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircleOutline,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = if (isCompleted) "Completed" else "Mark Done", fontSize = 12.sp)
                }
            }
        }

        // Chapter Header Card (Summary Focus)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("chapter_summary_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Surface(
                        color = CelestialCyan.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = chapter.partTitle.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = CelestialCyan,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = chapter.chapterTitle,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "CHAPTER SUMMARY",
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = chapter.summary,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 24.sp
                    )
                }
            }
        }

        // Primary Universal Principle Anchor Card
        if (associatedPrinciple != null) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("associated_principle_card")
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            if (onPrincipleClick != null) {
                                onPrincipleClick(associatedPrinciple.id)
                            }
                        },
                    colors = CardDefaults.cardColors(containerColor = CosmicNavyCardElevated),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                        .background(GoldDark),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Stars,
                                        contentDescription = null,
                                        tint = GoldLight,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "GOVERNING UNIVERSAL LAW",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GoldPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Text(
                                text = "View Law →",
                                style = MaterialTheme.typography.labelSmall,
                                color = CelestialCyan,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = associatedPrinciple.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "\"${associatedPrinciple.coreAxiom}\"",
                                style = MaterialTheme.typography.bodyMedium,
                                fontStyle = FontStyle.Italic,
                                color = GoldLight,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            }
        }

        // Key Principles & Axioms Section
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("key_principles_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = GoldPrimary,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Key Principles & Takeaways",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    chapter.keyTakeaways.forEach { takeaway ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(text = "✦", color = GoldPrimary, modifier = Modifier.padding(end = 8.dp))
                            Text(
                                text = takeaway,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Deep Dive Sections
        item {
            Text(
                text = "In-Depth Analytical Study",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        items(chapter.deepDiveSections) { section ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CosmicNavyCardElevated),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(GoldPrimary)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = section.heading,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = GoldLight
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = section.body,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        // Chapter Practice Exercise Module (Multiple-Choice Core Principles Check)
        item {
            ChapterPracticeQuizModule(
                question = chapterQuizQuestion,
                chapterTitle = chapter.chapterTitle
            )
        }

        // Alignment Exercise
        item {
            Text(
                text = "Applied Alignment Protocol",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            ExerciseCard(exercise = chapter.alignmentExercise)
        }

        // Reflection Journal
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("reflection_journal_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = null,
                            tint = CelestialCyan,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Deep Reflection Journal",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Select a contemplation prompt:",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    chapter.reflectionQuestions.forEach { prompt ->
                        val isSelected = prompt == selectedPrompt
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSelected) GoldDark.copy(alpha = 0.35f) else CosmicNavyCardElevated,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = { selectedPrompt = prompt }
                        ) {
                            Text(
                                text = prompt,
                                style = MaterialTheme.typography.bodySmall,
                                color = if (isSelected) GoldLight else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = journalText,
                        onValueChange = {
                            journalText = it
                            journalSavedFeedback = false
                        },
                        placeholder = { Text("Write your honest contemplation here...", color = TextMuted) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .testTag("journal_input_text"),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CelestialCyan,
                            unfocusedBorderColor = CosmicNavyBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (journalSavedFeedback) {
                            Text(
                                text = "✓ Entry recorded in Journal",
                                color = GoldLight,
                                style = MaterialTheme.typography.labelSmall
                            )
                        } else {
                            Spacer(modifier = Modifier.width(1.dp))
                        }

                        Button(
                            onClick = {
                                if (journalText.isNotBlank()) {
                                    onSaveJournal(selectedPrompt, journalText)
                                    journalText = ""
                                    journalSavedFeedback = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CelestialCyan,
                                contentColor = CosmicNavyCardElevated
                            ),
                            shape = RoundedCornerShape(8.dp),
                            enabled = journalText.isNotBlank(),
                            modifier = Modifier.testTag("btn_save_journal_entry")
                        ) {
                            Text("Save Reflection", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
