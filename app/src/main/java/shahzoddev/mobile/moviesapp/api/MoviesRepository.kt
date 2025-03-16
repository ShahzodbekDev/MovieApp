package shahzoddev.mobile.moviesapp.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import shahzoddev.mobile.moviesapp.api.model.Genre
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.api.model.MovieResult
import shahzoddev.mobile.moviesapp.api.model.MoviesApiResponse
import shahzoddev.mobile.moviesapp.api.model.RetrofitClient
import shahzoddev.mobile.moviesapp.api.model.RetrofitClient.apiService
import shahzoddev.mobile.moviesapp.ui.search.SearchPagingSource


class MoviesRepository {
    private val api = RetrofitClient.apiService


    suspend fun getMoviesByPage(page: Int): MoviesApiResponse? = withContext(Dispatchers.IO) {
        api.getMoviesByPage(page).body().takeIf { it != null }
    }


    suspend fun getMovieById(id: String): MovieResult? {
        return withContext(Dispatchers.IO) {
            val response = api.getMovieById(id)
            if (response.isSuccessful) response.body() else null
        }
    }

    suspend fun getGenres(): List<Genre>? {
        return withContext(Dispatchers.IO) {
            val response = api.getGenres()
            if (response.isSuccessful) response.body() else null
        }
    }


    fun searchMovies(query: String): Flow<PagingData<MovieListResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(apiService, query) }
        ).flow
    }

}