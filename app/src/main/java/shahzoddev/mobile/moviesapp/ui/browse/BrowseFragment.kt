package shahzoddev.mobile.moviesapp.ui.browse

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import shahzoddev.mobile.moviesapp.api.model.Genre
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.databinding.FragmentBrowseBinding
import shahzoddev.mobile.moviesapp.ui.browse.adapters.BrowseGenresAdapter
import shahzoddev.mobile.moviesapp.ui.browse.adapters.FilteredMovieAdapter
import shahzoddev.mobile.moviesapp.util.BaseFragment
import shahzoddev.mobile.moviesapp.viewModel.MoviesViewModel


class BrowseFragment : BaseFragment<FragmentBrowseBinding>(FragmentBrowseBinding::inflate) {

    private lateinit var browseGenresAdapter: BrowseGenresAdapter
    private lateinit var filteredMoviesAdapter: FilteredMovieAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private var selectedGenre: Genre? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        moviesViewModel.loadGenres()
        moviesViewModel.loadMovies(7)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initUI()



    }


    private fun initUI() = with(binding) {

        browseGenresAdapter = BrowseGenresAdapter(emptyList(), this@BrowseFragment::onClickGenre)
        genresList.adapter = browseGenresAdapter
        genresList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        filteredMoviesAdapter = FilteredMovieAdapter(emptyList() ,this@BrowseFragment::onClick)
        browseMovieList.apply {
            layoutManager = GridLayoutManager(context, 2)
           adapter = filteredMoviesAdapter
        }


        moviesViewModel.genres.observe(viewLifecycleOwner) { genres ->
            browseGenresAdapter.updateData(genres)

            if (genres.isNotEmpty() && selectedGenre == null) {
                selectedGenre = genres[0]
                browseGenresAdapter.setSelectedGenre(selectedGenre!!)
            }
        }

        moviesViewModel.movies.observe(viewLifecycleOwner){movie ->
            filteredMoviesAdapter.updateData(movie)
        }

    }

    private fun onClick(movie: MovieListResult) {
        findNavController().navigate(BrowseFragmentDirections.actionDetialsFragment(movie.id.toString()))
    }

    private fun onClickGenre(genre: Genre){
            selectedGenre = genre
            browseGenresAdapter.setSelectedGenre(genre)
    }
}
