package shahzoddev.mobile.moviesapp.api.model


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiService {


    @GET("movies")
    suspend fun getMovies(): Response<MoviesApiResponse>


    @GET("movies/{id}")
    suspend fun getMovieById(@Path("id") movieId: Int): Response<MovieResult>


    @GET("genres")
    suspend fun getGenres(): Response<List<Genre>>
}
