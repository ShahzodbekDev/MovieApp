package shahzoddev.mobile.moviesapp.api.model


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {


    @GET("movies")
    suspend fun getMoviesByPage(
        @Query("page") page: Int
    ): Response<MoviesApiResponse>


    @GET("movies")
    suspend fun searchMovies(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Response<MoviesApiResponse>


    @GET("movies/{id}")
    suspend fun getMovieById(@Path("id") id: String): Response<MovieResult>


    @GET("genres")
    suspend fun getGenres(): Response<List<Genre>>


}
