package com.example.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ui.screens.ChapterDetailView
import com.example.ui.screens.ChaptersListContent
import com.example.ui.screens.JournalAndQuizScreen
import com.example.ui.screens.OverviewScreen
import com.example.ui.screens.PracticeScreen
import com.example.ui.screens.PrincipleDetailView
import com.example.ui.screens.PrinciplesListContent
import com.example.viewmodel.ScreenTab
import com.example.viewmodel.StudyGuideViewModel

object Destinations {
    const val OVERVIEW = "overview"
    const val CHAPTERS = "chapters"
    const val CHAPTER_DETAIL = "chapter_detail/{chapterId}"
    const val PRINCIPLES = "principles"
    const val PRINCIPLE_DETAIL = "principle_detail/{principleId}"
    const val PRACTICE = "practice"
    const val JOURNAL = "journal"

    fun chapterDetail(chapterId: Int) = "chapter_detail/$chapterId"
    fun principleDetail(principleId: String) = "principle_detail/$principleId"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: StudyGuideViewModel,
    modifier: Modifier = Modifier
) {
    val completedChapters by viewModel.completedChapters.collectAsState()
    val bookmarkedPrinciples by viewModel.bookmarkedPrinciples.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Destinations.OVERVIEW,
        modifier = modifier.fillMaxSize(),
        enterTransition = { fadeIn() + slideInHorizontally { 300 } },
        exitTransition = { fadeOut() + slideOutHorizontally { -300 } },
        popEnterTransition = { fadeIn() + slideInHorizontally { -300 } },
        popExitTransition = { fadeOut() + slideOutHorizontally { 300 } }
    ) {
        composable(Destinations.OVERVIEW) {
            OverviewScreen(
                viewModel = viewModel,
                onNavigateToChapters = {
                    navController.navigate(Destinations.CHAPTERS)
                },
                onNavigateToPrinciples = {
                    navController.navigate(Destinations.PRINCIPLES)
                },
                onNavigateToPractice = {
                    navController.navigate(Destinations.PRACTICE)
                }
            )
        }

        composable(Destinations.CHAPTERS) {
            ChaptersListContent(
                viewModel = viewModel,
                onChapterClick = { chapterId ->
                    navController.navigate(Destinations.chapterDetail(chapterId))
                }
            )
        }

        composable(
            route = Destinations.CHAPTER_DETAIL,
            arguments = listOf(
                navArgument("chapterId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val chapterId = backStackEntry.arguments?.getInt("chapterId") ?: 1
            val chapter = viewModel.allChapters.find { it.id == chapterId }
                ?: viewModel.allChapters.first()

            ChapterDetailView(
                chapter = chapter,
                isCompleted = completedChapters.contains(chapter.id),
                onToggleComplete = { viewModel.toggleChapterCompletion(chapter.id) },
                onBack = { navController.popBackStack() },
                onSaveJournal = { prompt, text -> viewModel.saveJournalEntry(chapter.id, prompt, text) },
                onPrincipleClick = { principleId ->
                    navController.navigate(Destinations.principleDetail(principleId))
                }
            )
        }

        composable(Destinations.PRINCIPLES) {
            PrinciplesListContent(
                viewModel = viewModel,
                onPrincipleClick = { principleId ->
                    navController.navigate(Destinations.principleDetail(principleId))
                }
            )
        }

        composable(
            route = Destinations.PRINCIPLE_DETAIL,
            arguments = listOf(
                navArgument("principleId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val principleId = backStackEntry.arguments?.getString("principleId") ?: "principle_1"
            val principle = viewModel.allPrinciples.find { it.id == principleId }
                ?: viewModel.allPrinciples.first()

            PrincipleDetailView(
                principle = principle,
                isBookmarked = bookmarkedPrinciples.contains(principle.id),
                onToggleBookmark = { viewModel.togglePrincipleBookmark(principle.id) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Destinations.PRACTICE) {
            PracticeScreen(viewModel = viewModel)
        }

        composable(Destinations.JOURNAL) {
            JournalAndQuizScreen(viewModel = viewModel)
        }
    }
}
