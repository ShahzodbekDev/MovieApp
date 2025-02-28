package shahzoddev.mobile.moviesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import shahzoddev.mobile.moviesapp.api.MovieRepository
import shahzoddev.mobile.moviesapp.api.model.MovieListResult

class MoviesViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    val bannerList = MutableLiveData<List<MovieListResult>>()

    val isLoading = MutableLiveData(true)

    init {
        loadBannerMovies()
    }

    private fun loadBannerMovies() {
        viewModelScope.launch {
            delay(1000)
            isLoading.postValue(true)

            val response = movieRepository.listMovies()
            response?.let {
                bannerList.postValue(it.data)
            }

            isLoading.postValue(false)
        }
    }


}

