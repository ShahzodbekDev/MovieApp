package shahzoddev.mobile.moviesapp.api.model


data class MoviesApiResult(
    val data: List<MovieListResult>
)


data class MovieListResult(
    val country: String,
    val genres: List<String>,
    val id: Int,
    val images: List<String>,
    val imdb_rating: String,
    val poster: String,
    val title: String,
    val year: String
)

data class MovieResult(
    val actors: String,
    val awards: String,
    val country: String,
    val director: String,
    val genres: List<String>,
    val id: Int,
    val images: List<String>,
    val imdb_id: String,
    val imdb_rating: String,
    val imdb_votes: String,
    val metascore: String,
    val plot: String,
    val poster: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val title: String,
    val type: String,
    val writer: String,
    val year: String
)

data class GenresResult(
    val id: Int,
    val name: String
)




