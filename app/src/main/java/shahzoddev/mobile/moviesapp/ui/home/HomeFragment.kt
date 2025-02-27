package shahzoddev.mobile.moviesapp.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.databinding.FragmentHomeBinding
import shahzoddev.mobile.moviesapp.ui.home.adapters.BannerAdapter
import shahzoddev.mobile.moviesapp.util.BaseFragment
import shahzoddev.mobile.moviesapp.util.HorizontalMarginItemDecoration
import shahzoddev.mobile.moviesapp.viewModel.MoviesViewModel
import kotlin.math.abs


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var moviesViewModel: MoviesViewModel


    private lateinit var autoScrollHandler: Handler
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            val nextItem = binding.bannerView.currentItem + 1
            binding.bannerView.setCurrentItem(nextItem, true)
            autoScrollHandler.postDelayed(this, 3000)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        initUI()

        moviesViewModel.loadBannerMovies()

        autoScrollHandler = Handler(Looper.getMainLooper())
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


        bannerAdapter = BannerAdapter(emptyList())


        bannerView.apply {
            adapter = bannerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL


            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    autoScrollHandler.removeCallbacks(autoScrollRunnable)
                    autoScrollHandler.postDelayed(autoScrollRunnable, 3000)
                }
            })


        }



        moviesViewModel.bannerList.observe(viewLifecycleOwner) { movies ->
            bannerAdapter.updateData(movies)
        }
    }

}