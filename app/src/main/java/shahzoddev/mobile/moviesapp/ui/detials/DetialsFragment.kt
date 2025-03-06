package shahzoddev.mobile.moviesapp.ui.detials

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.databinding.FragmentDetialsBinding
import shahzoddev.mobile.moviesapp.ui.detials.adapters.GenresAdapter
import shahzoddev.mobile.moviesapp.ui.detials.adapters.MovieListAdapter
import shahzoddev.mobile.moviesapp.util.BaseFragment
import shahzoddev.mobile.moviesapp.util.MarginItemDecoration
import shahzoddev.mobile.moviesapp.viewModel.MoviesViewModel

class DetialsFragment : BaseFragment<FragmentDetialsBinding>(FragmentDetialsBinding::inflate) {

    private lateinit var moviesViewModel: MoviesViewModel
    private val args by navArgs<DetialsFragmentArgs>()
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var genresAdapter: GenresAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        moviesViewModel.loadMovies()
        moviesViewModel.loadMovie(args.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

    }

    private fun initUI() = with(binding) {
        save.setOnClickListener {
            it.isSelected = !it.isSelected
        }
        back.setOnClickListener {
            findNavController().popBackStack()
        }

        moviesViewModel.movieDetails.observe(viewLifecycleOwner) { movieDetails ->
            movieDetails?.let { m ->


                Glide.with(root).load(m.poster).into(detialsimage)
                movieName.text = getString(R.string.detials_movie_name, m.title)
                movieReleased.text = getString(R.string.detials_movie_released, m.year)


                vote.text = getString(R.string.detials_movie_vote, formatVotes(m.imdb_votes))
                duration.text = getString(R.string.detials_movie_duration, m.runtime)
                rating.text = getString(R.string.detials_movie_rating, m.imdb_rating)

                Glide.with(root)
                    .load(m.images.getOrNull(0))
                    .error(R.drawable.image_erorr)
                    .into(screenshotImage1)

                Glide.with(root)
                    .load(m.images.getOrNull(1))
                    .error(R.drawable.image_erorr)
                    .into(screenshotImage2)

                Glide.with(root)
                    .load(m.images.getOrNull(2))
                    .error(R.drawable.image_erorr)
                    .into(screenshotImage3)


                summaryText.text = getString(R.string.detials_summart_text, m.plot)
                actors.text = getString(R.string.detials_actors, m.actors)


                genresAdapter = GenresAdapter(m.genres)
                detialsGenresList.apply {
                    layoutManager = GridLayoutManager(context, 3)
                    adapter = genresAdapter
                    addItemDecoration(MarginItemDecoration(16))
                }


            }
        }

        movieListAdapter = MovieListAdapter(emptyList(), this@DetialsFragment::onClick)
        detialsMoviesList. apply {
            adapter = movieListAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        moviesViewModel.movies.observe(viewLifecycleOwner) { movies ->
            val limitedMovies = movies.take(4)
            movieListAdapter.updateData(limitedMovies)
        }
    }


    private fun onClick(movie: MovieListResult) {
        findNavController().navigate(DetialsFragmentDirections.actionDetialsFragment(movie.id.toString()))
    }

    private
    fun formatVotes(votes: String): String {
        return try {
            val voteCount = votes.replace(",", "").toLong()
            when {
                voteCount >= 1_000_000 -> {
                    val formatted = voteCount / 1_000_000.0
                    String.format("%.1fml", formatted)
                }
                else -> voteCount.toString()
            }
        } catch (e: Exception) {
            votes
        }
    }
}