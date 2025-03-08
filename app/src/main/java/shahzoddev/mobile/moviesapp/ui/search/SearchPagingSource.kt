package shahzoddev.mobile.moviesapp.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.api.model.MoviesApiService

class SearchPagingSource(
    private val apiService: MoviesApiService,
    private val query: String
) : PagingSource<Int, MovieListResult>() {

    override fun getRefreshKey(state: PagingState<Int, MovieListResult>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListResult> {
        val page = params.key ?: 1
        return try {
            val response = apiService.searchMovies(query, page)
            val movies = response.body()?.data ?: emptyList()
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}


