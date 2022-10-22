package dev.ahart.movielist.trending.retrofit

import dev.ahart.movielist.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface MovieRetrofitService {

  /**
   * Fetches the trending movies from the api endpoint
   */
  @GET(TheMovieDatabaseConstants.TRENDING)
  suspend fun getTrending(): TrendingMovies

  companion object {
    /**
     * Creates a new instance of our retrofit service. Ensures all API
     * calls are appended with the API key.
     */
    fun createService(): MovieRetrofitService {
      val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
          chain.proceed(
            chain.request().newBuilder()
              .url(
                chain.request().url.newBuilder()
                  .addQueryParameter("api_key", BuildConfig.API_KEY)
                  .build()
              )
              .build()
          )
        }
        .build()

      return Retrofit.Builder()
        .client(client)
        .baseUrl(TheMovieDatabaseConstants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(MovieRetrofitService::class.java)
    }
  }
}