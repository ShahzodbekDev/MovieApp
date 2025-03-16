package shahzoddev.mobile.moviesapp.ui.user.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import shahzoddev.mobile.moviesapp.ui.user.HistoryFragment
import shahzoddev.mobile.moviesapp.ui.user.WatchListFragment

class TabPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WatchListFragment()
            1 -> HistoryFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}

