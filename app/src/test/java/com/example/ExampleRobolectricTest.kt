package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.StudyGuideData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Law Behind Laws", appName)
  }

  @Test
  fun `study guide data contains all chapters and principles`() {
    assertEquals(7, StudyGuideData.chapters.size)
    assertEquals(7, StudyGuideData.universalPrinciples.size)
    assertTrue(StudyGuideData.quizQuestions.isNotEmpty())
  }

  @Test
  fun `each chapter has a matching multiple choice practice question`() {
    StudyGuideData.chapters.forEach { chapter ->
      val q = StudyGuideData.quizQuestions.find { it.id == chapter.id }
      assertNotNull("Chapter ${chapter.id} should have a practice question", q)
      assertTrue("Question should have at least 2 options", (q?.options?.size ?: 0) >= 2)
      assertTrue("Correct index should be within bounds", (q?.correctIndex ?: -1) < (q?.options?.size ?: 0))
      assertTrue("Explanation should not be empty", q?.explanation?.isNotBlank() == true)
    }
  }
}
