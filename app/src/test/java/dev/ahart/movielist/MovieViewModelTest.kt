package dev.ahart.movielist

import dev.ahart.movielist.trending.MovieViewModel
import dev.ahart.movielist.trending.retrofit.MovieRetrofitService
import dev.ahart.movielist.trending.retrofit.TrendingMovie
import dev.ahart.movielist.trending.retrofit.TrendingMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

private const val MOVIE_COUNT = 10

class MovieViewModelTest {

  private val testDispatcher = UnconfinedTestDispatcher()

  @Before
  fun setUp() {
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `Given my happy path, when I init, then I expect 10 named movies`() {
    val testSubject = MovieViewModel(FakeHappyMovieRetrofitService, testDispatcher)

    val state = testSubject.uiState

    assertEquals(state.size, MOVIE_COUNT)
    state.forEachIndexed { index, movie ->
      assertEquals(movie.name, "Movie $index")
    }
  }

  @Test
  fun `Given a movie with no name and no title, when I init, then I expect empty string`() {
    val testSubject = MovieViewModel(FakeNoNameOrTitleMovieRetrofitService, testDispatcher)

    val state = testSubject.uiState

    assertEquals("", state.first().name)
  }

  @Test
  fun `Given no trending movies, when I init, then I expect nothing`() {
    val testSubject = MovieViewModel(FakeEmptyMovieRetrofitService, testDispatcher)

    val state = testSubject.uiState

    assertTrue(state.isEmpty())
  }

  /**
   * Happy path retrofit service, where each entry has either a name
   * or a title.
   */
  object FakeHappyMovieRetrofitService : MovieRetrofitService {
    override suspend fun getTrending(): TrendingMovies {
      return TrendingMovies(
        (0 until MOVIE_COUNT).map {
          val movieName = "Movie $it"
          TrendingMovie(
            if (it % 2 == 0) movieName else null,
            if (it % 2 == 1) movieName else null,
            poster_path = "/$it"
          )
        }
      )
    }
  }

  object FakeNoNameOrTitleMovieRetrofitService : MovieRetrofitService {
    override suspend fun getTrending(): TrendingMovies {
      return TrendingMovies(
        listOf(
          TrendingMovie(
            null, null, ""
          )
        )
      )
    }
  }

  object FakeEmptyMovieRetrofitService : MovieRetrofitService {
    override suspend fun getTrending(): TrendingMovies {
      return TrendingMovies(emptyList())
    }
  }
}