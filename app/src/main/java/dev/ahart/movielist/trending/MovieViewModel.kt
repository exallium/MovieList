package dev.ahart.movielist.trending

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {
  var uiState by mutableStateOf<List<Movie>>(emptyList())
    private set
}