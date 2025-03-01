package shahzoddev.mobile.moviesapp.api.model

data class AllResults(
    val moviesApiResult: MoviesApiResponse?,  // API-dan barcha filmlar
    val movieResult: MovieResult?,            // Bitta film ma'lumotlari
    val genresResult: List<Genre>?            // Janrlar ro‘yxati (oddiy list ko‘rinishida)
)

// API-dan barcha filmlar ro‘yxati
data class MoviesApiResponse(
    val data: List<MovieListResult>
)

// Bitta filmning qisqacha versiyasi (list uchun)
data class MovieListResult(
    val id: Int,
    val title: String,
    val poster: String,
    val year: String,
    val country: String,
    val imdb_rating: String,
    val genres: List<String>,
    val images: List<String>
)

// Bitta filmning to‘liq ma’lumotlari
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

// Janrlar listi
data class Genre(
    val id: Int,
    val name: String
)






