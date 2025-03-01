package shahzoddev.mobile.moviesapp.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import shahzoddev.mobile.moviesapp.api.model.Genre
import shahzoddev.mobile.moviesapp.api.model.MovieResult
import shahzoddev.mobile.moviesapp.api.model.MoviesApiResponse
import shahzoddev.mobile.moviesapp.api.model.RetrofitClient


class MoviesRepository {
    private val api = RetrofitClient.apiService

    suspend fun getMovies(): MoviesApiResponse? {
        return withContext(Dispatchers.IO) {
            val response = api.getMovies()
            if (response.isSuccessful) response.body() else null
        }
    }

    suspend fun getMovieById(movieId: Int): MovieResult? {
        return withContext(Dispatchers.IO) {
            val response = api.getMovieById(movieId)
            if (response.isSuccessful) response.body() else null
        }
    }

    suspend fun getGenres(): List<Genre>? {
        return withContext(Dispatchers.IO) {
            val response = api.getGenres()
            if (response.isSuccessful) response.body() else null
        }
    }
}