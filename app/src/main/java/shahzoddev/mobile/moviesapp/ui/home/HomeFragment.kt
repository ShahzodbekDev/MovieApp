package shahzoddev.mobile.moviesapp.ui.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.databinding.FragmentHomeBinding
import shahzoddev.mobile.moviesapp.ui.home.adapters.BannerAdapter
import shahzoddev.mobile.moviesapp.ui.home.adapters.MoviesHomeAdapter
import shahzoddev.mobile.moviesapp.util.BaseFragment
import shahzoddev.mobile.moviesapp.util.HorizontalMarginItemDecoration
import shahzoddev.mobile.moviesapp.util.OverscrollUtil
import shahzoddev.mobile.moviesapp.viewModel.MoviesViewModel
import kotlin.math.abs


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var moviesHomeAdapter: MoviesHomeAdapter
    private lateinit var moviesViewModel: MoviesViewModel


    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            _binding?.let {
                val itemCount = bannerAdapter.itemCount
                if (itemCount > 0) {
                    val nextItem = (binding.bannerView.currentItem + 1) % itemCount
                    binding.bannerView.setCurrentItem(nextItem, true)
                }
                postRunnable(this, 3000) 
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        moviesViewModel.loadMovies()

        initUI()


        val shimmerLayout = binding.homeShimmerEffect
        shimmerLayout.apply {
            startShimmer()
            visibility = View.VISIBLE
        }


        binding.home.visibility = View.GONE


        moviesViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                shimmerLayout.apply {
                    startShimmer()
                    visibility = View.VISIBLE
                }
                binding.home.visibility = View.GONE
            }
        }


        fun stopShimmerAndShowContent() {
            shimmerLayout.apply {
                stopShimmer()
                visibility = View.GONE
            }
            binding.home.apply {
                visibility = View.VISIBLE
                OverscrollUtil.enableScrollViewOverscroll(this)
            }

        }

        moviesViewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (!movies.isNullOrEmpty()) {
                stopShimmerAndShowContent()
                bannerAdapter.updateData(movies)
            }
        }


        postRunnable(autoScrollRunnable, 3000)
    }


    private fun initUI() = with(binding) {


        bannerView.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.15f * abs(position))

        }
        bannerView.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        bannerView.addItemDecoration(itemDecoration)



        bannerAdapter = BannerAdapter(emptyList(), this@HomeFragment::onClick)

        bannerView.apply {
            adapter = bannerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL


            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)


                    clearRunnables()

                    postRunnable(autoScrollRunnable, 3000)
                }
            })
        }

        bannerView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val currentBanner = bannerAdapter.getItem(position)

                Glide.with(requireContext())
                    .load(currentBanner.poster)
                    .transition(DrawableTransitionOptions.withCrossFade(1000))
                    .into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            binding.bannerLayout.background = resource
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
            }
        })



        
        moviesHomeAdapter = MoviesHomeAdapter(emptyList(), this@HomeFragment::onClick)
        moviesList.apply {
            adapter = moviesHomeAdapter
            OverscrollUtil.enableRecyclerViewOverscroll(this, true)
        }


        moviesViewModel.movies.observe(viewLifecycleOwner) { banner ->
            bannerAdapter.updateData(banner)
        }
        moviesViewModel.movies.observe(viewLifecycleOwner) { movies ->
            moviesHomeAdapter.updateData(movies)
        }
    }

    private fun onClick(movie: MovieListResult) {
        findNavController().navigate(HomeFragmentDirections.actionDetialsFragment(movie.id.toString()))
    }




}