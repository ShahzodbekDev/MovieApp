package shahzoddev.mobile.moviesapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.api.model.MoviesApiService
import shahzoddev.mobile.moviesapp.api.model.RetrofitClient.apiService
import shahzoddev.mobile.moviesapp.databinding.FragmentSearchBinding
import shahzoddev.mobile.moviesapp.ui.search.adapters.SearchAdapter
import shahzoddev.mobile.moviesapp.util.BaseFragment
import shahzoddev.mobile.moviesapp.util.MarginItemDecoration
import shahzoddev.mobile.moviesapp.viewModel.MoviesViewModel


class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
            searchAdapter = SearchAdapter(this@SearchFragment::onClick)

            binding.searchResultList.apply {
                adapter = searchAdapter
                layoutManager = GridLayoutManager(context, 2)
            }


            binding.search.doOnTextChanged { text, _, _, _ ->
                moviesViewModel.searchMovies(text.toString().trim())
            }


            lifecycleScope.launch {
                moviesViewModel.searchResults.collectLatest { pagingData ->
                    searchAdapter.submitData(pagingData)
                }


            }


            lifecycleScope.launch {
                searchAdapter.loadStateFlow.collectLatest { loadStates ->

                    binding.linearProgress.isVisible = loadStates.source.append is LoadState.Loading

                    val isListEmpty = searchAdapter.snapshot().isEmpty() && loadStates.source.refresh is LoadState.NotLoading
                    binding.searchEpty.isVisible = isListEmpty
                }
            }

        }


    private fun onClick(movie : MovieListResult){
        findNavController().navigate(SearchFragmentDirections.actionDetialsFragment(movie.id.toString()))
    }


}