package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigation.AppNavHost
import com.example.navigation.Destinations
import com.example.ui.theme.CosmicNavyCard
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.LawStudyGuideTheme
import com.example.ui.theme.TextMuted
import com.example.viewmodel.StudyGuideViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LawStudyGuideTheme {
                StudyGuideApp()
            }
        }
    }
}

@Composable
fun StudyGuideApp(viewModel: StudyGuideViewModel = viewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .testTag("bottom_nav_bar"),
                containerColor = CosmicNavyCard,
                contentColor = GoldPrimary
            ) {
                // Overview Tab
                val isOverviewSelected = currentRoute == Destinations.OVERVIEW
                NavigationBarItem(
                    selected = isOverviewSelected,
                    onClick = {
                        if (!isOverviewSelected) {
                            navController.navigate(Destinations.OVERVIEW) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (isOverviewSelected) Icons.Filled.Home else Icons.Outlined.Home,
                            contentDescription = "Overview",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = "Overview",
                            fontSize = 11.sp,
                            fontWeight = if (isOverviewSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = navigationItemColors(),
                    modifier = Modifier.testTag("tab_overview")
                )

                // Chapters Tab (Navigation Component focus)
                val isChaptersSelected = currentRoute == Destinations.CHAPTERS || currentRoute?.startsWith("chapter_detail") == true
                NavigationBarItem(
                    selected = isChaptersSelected,
                    onClick = {
                        if (currentRoute != Destinations.CHAPTERS) {
                            navController.navigate(Destinations.CHAPTERS) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (isChaptersSelected) Icons.Filled.Book else Icons.Outlined.Book,
                            contentDescription = "Chapters",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = "Chapters",
                            fontSize = 11.sp,
                            fontWeight = if (isChaptersSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = navigationItemColors(),
                    modifier = Modifier.testTag("tab_chapters")
                )

                // Principles Tab
                val isPrinciplesSelected = currentRoute == Destinations.PRINCIPLES || currentRoute?.startsWith("principle_detail") == true
                NavigationBarItem(
                    selected = isPrinciplesSelected,
                    onClick = {
                        if (currentRoute != Destinations.PRINCIPLES) {
                            navController.navigate(Destinations.PRINCIPLES) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (isPrinciplesSelected) Icons.Filled.AutoAwesome else Icons.Outlined.AutoAwesome,
                            contentDescription = "Principles",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = "Principles",
                            fontSize = 11.sp,
                            fontWeight = if (isPrinciplesSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = navigationItemColors(),
                    modifier = Modifier.testTag("tab_principles")
                )

                // Practice Tab
                val isPracticeSelected = currentRoute == Destinations.PRACTICE
                NavigationBarItem(
                    selected = isPracticeSelected,
                    onClick = {
                        if (!isPracticeSelected) {
                            navController.navigate(Destinations.PRACTICE) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (isPracticeSelected) Icons.Filled.FitnessCenter else Icons.Outlined.FitnessCenter,
                            contentDescription = "Practice",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = "Practice",
                            fontSize = 11.sp,
                            fontWeight = if (isPracticeSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = navigationItemColors(),
                    modifier = Modifier.testTag("tab_practice")
                )

                // Journal Tab
                val isJournalSelected = currentRoute == Destinations.JOURNAL
                NavigationBarItem(
                    selected = isJournalSelected,
                    onClick = {
                        if (!isJournalSelected) {
                            navController.navigate(Destinations.JOURNAL) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (isJournalSelected) Icons.Filled.HistoryEdu else Icons.Outlined.HistoryEdu,
                            contentDescription = "Journal & Exam",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = "Journal",
                            fontSize = 11.sp,
                            fontWeight = if (isJournalSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = navigationItemColors(),
                    modifier = Modifier.testTag("tab_journal")
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AppNavHost(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun navigationItemColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = GoldLight,
    unselectedIconColor = TextMuted,
    selectedTextColor = GoldPrimary,
    unselectedTextColor = TextMuted,
    indicatorColor = GoldDark.copy(alpha = 0.35f)
)
