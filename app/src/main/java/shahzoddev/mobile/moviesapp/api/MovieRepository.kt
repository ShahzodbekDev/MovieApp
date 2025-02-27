package shahzoddev.mobile.moviesapp.api

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shahzoddev.mobile.moviesapp.api.model.MovieResult
import shahzoddev.mobile.moviesapp.api.model.MoviesApiResult
import shahzoddev.mobile.moviesapp.api.model.ApiService


// https://moviesapi.ir/api/v1/movies
class MovieRepository {

    private val service: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://moviesapi.ir/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)
    }


    suspend fun listMovies(): MoviesApiResult? {
        return try {
            service.listMovies()
        } catch (e: Exception) {
            Log.e("API_ERROR", "Xatolik: ${e.message}")
            null
        }
    }


    suspend fun getMovie(id: Int): MovieResult? {
        return try {
            service.getMovie(id)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Xatolik: ${e.message}")
            null
        }
    }
}