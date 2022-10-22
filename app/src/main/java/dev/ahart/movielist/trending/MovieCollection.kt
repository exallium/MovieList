package dev.ahart.movielist.trending

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.ahart.movielist.ui.theme.MovieListTheme

@Preview
@Composable
fun MovieCollectionPreview() {
  MovieCollection((1..10).map {
    Movie(
      uri = "$it",
      name = "Name"
    )
  })
}

@Composable
fun MovieCollection(movies: List<Movie>) {
  MovieListTheme {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)
    ) {
      items(
        count = movies.size,
        key = { movies[it].uri },
      ) {
        MovieItem(movies[it])
      }
    }
  }
}

@Composable
fun MovieItem(movie: Movie) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .height(56.dp)
      .padding(horizontal = 24.dp)
  ) {
    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current)
        .data(movie.uri)
        .crossfade(true)
        .build(),
      placeholder = ColorPainter(MaterialTheme.colors.secondary),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .size(48.dp)
        .clip(RoundedCornerShape(8.dp))
    )
    Text(
      text = movie.name,
      modifier = Modifier.padding(start = 16.dp),
      style = MaterialTheme.typography.h6
    )
  }
}