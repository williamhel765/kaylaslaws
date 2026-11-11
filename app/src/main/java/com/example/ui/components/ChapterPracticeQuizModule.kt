package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.QuizQuestion
import com.example.ui.theme.CelestialCyan
import com.example.ui.theme.CosmicNavyBorder
import com.example.ui.theme.CosmicNavyCardElevated
import com.example.ui.theme.CosmicNavyDark
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.TextMuted

@Composable
fun ChapterPracticeQuizModule(
    question: QuizQuestion,
    chapterTitle: String,
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember(question.id) { mutableStateOf<Int?>(null) }
    val isSubmitted = selectedIndex != null

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("chapter_practice_quiz_module"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Module Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(GoldDark.copy(alpha = 0.35f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Psychology,
                            contentDescription = null,
                            tint = GoldPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "PRACTICE EXERCISE",
                            style = MaterialTheme.typography.labelSmall,
                            color = GoldPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                        Text(
                            text = "Core Principles Check",
                            style = MaterialTheme.typography.labelSmall,
                            color = CelestialCyan
                        )
                    }
                }

                if (isSubmitted) {
                    val isCorrect = selectedIndex == question.correctIndex
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (isCorrect) Color(0xFF1B5E20).copy(alpha = 0.25f) else Color(0xFFB71C1C).copy(alpha = 0.25f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                                contentDescription = null,
                                tint = if (isCorrect) Color(0xFF4CAF50) else Color(0xFFEF5350),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isCorrect) "Correct" else "Needs Review",
                                color = if (isCorrect) Color(0xFF4CAF50) else Color(0xFFEF5350),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Question Statement
            Text(
                text = question.question,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp,
                modifier = Modifier.testTag("practice_quiz_question_text")
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Multiple-Choice Options
            question.options.forEachIndexed { index, option ->
                val isThisOptionSelected = selectedIndex == index
                val isCorrect = index == question.correctIndex

                val backgroundColor = when {
                    !isSubmitted -> CosmicNavyCardElevated
                    isCorrect -> Color(0xFF1B5E20).copy(alpha = 0.45f) // Green for correct answer
                    isThisOptionSelected && !isCorrect -> Color(0xFFB71C1C).copy(alpha = 0.45f) // Red for wrong selection
                    else -> CosmicNavyCardElevated.copy(alpha = 0.6f)
                }

                val borderColor = when {
                    !isSubmitted -> CosmicNavyBorder
                    isCorrect -> Color(0xFF4CAF50)
                    isThisOptionSelected && !isCorrect -> Color(0xFFEF5350)
                    else -> CosmicNavyBorder
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = backgroundColor,
                    border = BorderStroke(1.dp, borderColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .testTag("practice_quiz_option_$index"),
                    onClick = {
                        // Immediately register user selection
                        if (!isSubmitted) {
                            selectedIndex = index
                        }
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = when {
                                isSubmitted && isCorrect -> Color(0xFF4CAF50)
                                isSubmitted && isThisOptionSelected && !isCorrect -> Color(0xFFEF5350)
                                else -> GoldDark.copy(alpha = 0.35f)
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "${('A'.code + index).toChar()}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isSubmitted && (isCorrect || isThisOptionSelected)) Color.White else GoldLight,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )

                        if (isSubmitted) {
                            if (isCorrect) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Correct",
                                    tint = Color(0xFF4CAF50),
                                    modifier = Modifier.size(20.dp)
                                )
                            } else if (isThisOptionSelected) {
                                Icon(
                                    imageVector = Icons.Default.Cancel,
                                    contentDescription = "Incorrect",
                                    tint = Color(0xFFEF5350),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Immediate Feedback Card (Visible immediately upon user selection)
            AnimatedVisibility(
                visible = isSubmitted,
                enter = fadeIn() + slideInVertically { 20 }
            ) {
                Column(modifier = Modifier.padding(top = 14.dp)) {
                    val isCorrect = selectedIndex == question.correctIndex

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isCorrect) Color(0xFF1B5E20).copy(alpha = 0.25f) else Color(0xFF3E2723).copy(alpha = 0.35f),
                        border = BorderStroke(
                            1.dp,
                            if (isCorrect) Color(0xFF4CAF50).copy(alpha = 0.5f) else Color(0xFFFFB74D).copy(alpha = 0.5f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("practice_quiz_feedback_card")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.HelpOutline,
                                    contentDescription = null,
                                    tint = if (isCorrect) Color(0xFF4CAF50) else Color(0xFFFFB74D),
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isCorrect) "IMMEDIATE FEEDBACK: EXCELLENT ALIGNMENT" else "IMMEDIATE FEEDBACK: REASSESSING THE PRINCIPLE",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isCorrect) Color(0xFF81C784) else Color(0xFFFFB74D),
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = question.explanation,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 20.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        OutlinedButton(
                            onClick = { selectedIndex = null },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = GoldLight
                            ),
                            border = BorderStroke(1.dp, CosmicNavyBorder),
                            modifier = Modifier.testTag("btn_try_again_practice_quiz")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Try Again", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}
