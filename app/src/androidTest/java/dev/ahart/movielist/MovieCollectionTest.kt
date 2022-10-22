package dev.ahart.movielist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.ahart.movielist.trending.Movie
import dev.ahart.movielist.trending.MovieCollection
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MovieCollectionTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun ensureNamesAppear() {
    val movies = (0 until 10)
      .map {
        Movie(
          uri = "$it",
          name = "Movie $it"
        )
      }

    composeTestRule.setContent {
      MovieCollection(movies)
    }

    movies.forEach {
      composeTestRule.onNodeWithText(it.name).assertIsDisplayed()
    }
  }
}