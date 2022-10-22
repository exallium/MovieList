package dev.ahart.movielist.trending

/**
 * Represents a single Movie we can display in our UI
 *
 * @param uri   The Movie's URI, that can be handed straight to coil
 * @param name  The name of the movie.
 */
data class Movie(
  val uri: String,
  val name: String
)