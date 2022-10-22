package dev.ahart.movielist.trending

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ahart.movielist.trending.retrofit.MovieRetrofitService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(
  private val movieRetrofitService: MovieRetrofitService = MovieRetrofitService.createService(),
  private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
  var uiState by mutableStateOf<List<Movie>>(emptyList())
    private set

  init {
    viewModelScope.launch {
      uiState = withContext(ioDispatcher) {
        val trending = movieRetrofitService.getTrending()
        trending.results.map {
          Movie(
            uri = it.getFullPosterPath(),
            name = listOfNotNull(it.title, it.name).firstOrNull() ?: ""
          )
        }
      }
    }
  }
}