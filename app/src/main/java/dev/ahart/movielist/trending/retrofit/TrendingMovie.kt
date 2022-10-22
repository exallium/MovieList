package dev.ahart.movielist.trending.retrofit

/**
 * A single trending movie. Both name and title are
 * nullable and necessary because of data formatting
 * irregularities.
 */
class TrendingMovie(
  val name: String?,
  val title: String?,
  private val poster_path: String
) {
  fun getFullPosterPath(): String {
    return "${TheMovieDatabaseConstants.IMAGE_BASE_URL}w92$poster_path"
  }
}