package shahzoddev.mobile.moviesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import shahzoddev.mobile.moviesapp.api.MovieRepository
import shahzoddev.mobile.moviesapp.api.model.MovieListResult

class MoviesViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    val bannerList = MutableLiveData<List<MovieListResult>>()

    fun loadBannerMovies() {
        viewModelScope.launch {
            val response = movieRepository.listMovies()
            response?.let {
                bannerList.postValue(it.data)
            }
        }
    }
}

