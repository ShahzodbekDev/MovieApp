package shahzoddev.mobile.moviesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import shahzoddev.mobile.moviesapp.api.MoviesRepository
import shahzoddev.mobile.moviesapp.api.model.Genre
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.api.model.MovieResult

class MoviesViewModel  : ViewModel() {

    private val repository = MoviesRepository()

    // LiveData - UI-ni kuzatib turish uchun
    private val _movies = MutableLiveData<List<MovieListResult>>()
    val movies: LiveData<List<MovieListResult>> get() = _movies


    private val _movieDetails = MutableLiveData<MovieResult>()
    val movieDetails: LiveData<MovieResult> get() = _movieDetails

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // Barcha filmlarni yuklash
    fun loadMovies() {
        _isLoading.value = true
        viewModelScope.launch {
            delay(1000)
            val response = repository.getMovies()
            _movies.postValue(response?.data ?: emptyList())
            _isLoading.postValue(false)
        }
    }

    // Bitta filmni yuklash
    fun loadMovie(id: String) {
        _isLoading.value = true
        viewModelScope.launch {
            delay(1000)
            val response = repository.getMovieById(id)
            response?.let {
                _movieDetails.postValue(it)
            }
            _isLoading.postValue(false)
        }
    }

    // Janrlarni yuklash
    fun loadGenres() {
        viewModelScope.launch {
            delay(1000)
            val response = repository.getGenres()
            _genres.postValue(response ?: emptyList())
        }
    }
}



