package shahzoddev.mobile.moviesapp.api.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movies")
    suspend fun listMovies(): MoviesApiResult

    @GET("movies/{id}")
    suspend fun getMovie(@Path("id") id: Int): MovieResult
}
