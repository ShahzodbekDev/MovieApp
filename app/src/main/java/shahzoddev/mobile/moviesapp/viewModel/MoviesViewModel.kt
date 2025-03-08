package shahzoddev.mobile.moviesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import shahzoddev.mobile.moviesapp.api.MoviesRepository
import shahzoddev.mobile.moviesapp.api.model.Genre
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.api.model.MovieResult

class MoviesViewModel() : ViewModel() {

    private val repository = MoviesRepository()

    // LiveData - UI-ni kuzatib turish uchun
    private val _movies = MutableLiveData<List<MovieListResult>>()
    val movies: LiveData<List<MovieListResult>> get() = _movies


    private val _movieDetails = MutableLiveData<MovieResult>()
    val movieDetails: LiveData<MovieResult> get() = _movieDetails

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    private val _searchQuery = MutableStateFlow("")  // Qidiruv so‘rovi
    val searchQuery: StateFlow<String> = _searchQuery

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // Barcha filmlarni yuklash
    fun loadMovies(page:Int) {
        _isLoading.value = true
        viewModelScope.launch {
            delay(1000)
            val response = repository.getMovies(page)
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


    val searchResults = searchQuery
        .debounce(300)  // 300ms kutib turadi
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                flowOf(PagingData.empty())
            } else {
                repository.searchMovies(query)
            }
        }
        .cachedIn(viewModelScope)

    fun searchMovies(query: String) {
        _searchQuery.value = query
    }


}



