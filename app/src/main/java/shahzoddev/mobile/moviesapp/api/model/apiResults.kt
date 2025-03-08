package shahzoddev.mobile.moviesapp.api.model


data class MoviesApiResponse(
    val data: List<MovieListResult>
)


data class MovieListResult(
    val id: Int,
    val title: String,
    val poster: String,
    val year: String,
    val country: String,
    val imdb_rating: String,
    val genres: List<String>,
    val images: List<String>? = null
)


data class MovieResult(
    val id: Int,
    val title: String,
    val poster: String,
    val year: String,
    val country: String,
    val imdb_rating: String,
    val genres: List<String>,
    val images: List<String>,
    val actors: String,
    val awards: String,
    val director: String,
    val imdb_id: String,
    val imdb_votes: String,
    val metascore: String,
    val plot: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val type: String,
    val writer: String
)

data class Genre(
    val id: Int,
    val name: String
)






