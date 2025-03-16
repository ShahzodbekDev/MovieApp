package shahzoddev.mobile.moviesapp.ui.detials

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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
import kotlin.random.Random

class DetialsFragment : BaseFragment<FragmentDetialsBinding>(FragmentDetialsBinding::inflate) {

    private lateinit var moviesViewModel: MoviesViewModel
    private val args by navArgs<DetialsFragmentArgs>()
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var genresAdapter: GenresAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        val randomPage = Random.nextInt(1, 26)
        moviesViewModel.loadMovies(randomPage)
        moviesViewModel.loadMovie(args.id)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        changeBgForScrull()

        val shimmerLayout = binding.detialsShimmerEffect
        shimmerLayout.apply {
            startShimmer()
            visibility = View.VISIBLE
        }

        binding.detials.visibility = View.GONE

        moviesViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                shimmerLayout.apply {
                    startShimmer()
                    visibility = View.VISIBLE
                }
                binding.detials.visibility = View.GONE
            }
        }

        fun stopShimmerAndShowContent() {
            shimmerLayout.apply {
                stopShimmer()
                visibility = View.GONE
            }
            binding.detials.apply {
                visibility = View.VISIBLE
            }

        }

        moviesViewModel.movies.observe(viewLifecycleOwner) { movies ->
            val limitedData = movies.take(4)
            if (!movies.isNullOrEmpty()) {
                stopShimmerAndShowContent()
                movieListAdapter.updateData(limitedData)
            }
        }


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


                val images = m.images ?: emptyList()

                images.getOrNull(0)?.let {
                    Glide.with(root)
                        .load(it)
                        .into(screenshotImage1)
                } ?: screenshotImage1.setImageResource(R.drawable.image_erorr)

                images.getOrNull(1)?.let {
                    Glide.with(root)
                        .load(it)
                        .into(screenshotImage2)
                } ?: screenshotImage2.setImageResource(R.drawable.image_erorr)

                images.getOrNull(2)?.let {
                    Glide.with(root)
                        .load(it)
                        .into(screenshotImage3)
                } ?: screenshotImage3.setImageResource(R.drawable.image_erorr)




                summaryText.text = getString(R.string.detials_summart_text, m.plot)
                actors.text = getString(R.string.detials_actors, m.actors)


                genresAdapter = GenresAdapter(m.genres)
                detialsGenresList.apply {
                    layoutManager = GridLayoutManager(context, 3)
                    adapter = genresAdapter
                    addItemDecoration(MarginItemDecoration(16, 16))
                }


            }
        }

        movieListAdapter = MovieListAdapter(emptyList(), this@DetialsFragment::onClick)
        detialsMoviesList.apply {
            adapter = movieListAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(MarginItemDecoration(16, 16))
        }

    }


    private fun onClick(movie: MovieListResult) {
        findNavController().navigate(DetialsFragmentDirections.actionDetialsFragment(movie.id.toString()))
    }

    @SuppressLint("DefaultLocale")
    private fun formatVotes(votes: String): String {
        return try {
            val voteCount = votes.replace(",", "").toLong()
            when {
                voteCount >= 1_000_000 -> {
                    val formatted = voteCount / 1_000_000.0
                    String.format("%.1fmln", formatted)
                }
                voteCount >= 100_000 -> {
                    val formatted = voteCount / 1000
                    String.format("%dk", formatted)
                }
                voteCount >= 10_000 -> {
                    val formatted = voteCount / 1000
                    String.format("%dk", formatted)
                }
                else -> voteCount.toString()
            }
        } catch (e: Exception) {
            votes
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeBgForScrull(){
        binding.detials.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > dpToPx(470)) {
                binding.backSaveBlock.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.blur_effect))
            } else {
                binding.backSaveBlock.background = null
            }
        }
    }
    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

}