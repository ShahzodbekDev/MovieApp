package shahzoddev.mobile.moviesapp.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHACKED_CAST")
class MovieViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MoviesViewModel() as T

    }

}