package dev.ahart.movielist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.ahart.movielist.trending.Movie
import dev.ahart.movielist.trending.MovieCollection
import dev.ahart.movielist.trending.MovieViewModel
import dev.ahart.movielist.ui.theme.MovieListTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MovieListTheme {
        val viewModel: MovieViewModel = viewModel()
        val movies: List<Movie> = viewModel.uiState
        MovieCollection(movies = movies)
      }
    }
  }
}