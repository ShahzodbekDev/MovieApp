package shahzoddev.mobile.moviesapp.ui.user

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import shahzoddev.mobile.moviesapp.PreferencesHelper
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.databinding.FragmentUserBinding
import shahzoddev.mobile.moviesapp.ui.user.adapters.TabPagerAdapter
import shahzoddev.mobile.moviesapp.util.BaseFragment


class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {

    private lateinit var tabPagerAdapter: TabPagerAdapter
    private lateinit var preferences: PreferencesHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = PreferencesHelper(requireContext())

        initUi()
    }

    private fun initUi() = with(binding) {

        val imageList = listOf(
            R.drawable.pick_avatar1,
            R.drawable.pick_avatar2,
            R.drawable.pick_avatar3,
            R.drawable.pick_avatar4,
            R.drawable.pick_avatar5,
            R.drawable.pick_avatar6,
            R.drawable.pick_avatar7,
            R.drawable.pick_avatar8,
            R.drawable.pick_avatar9,
        )

        val selectedAvatarId = preferences.getPickAvatar()

        if (selectedAvatarId in imageList.indices) {
            binding.userAvatar.setImageResource(imageList[selectedAvatarId])
        } else {
            binding.userAvatar.setImageResource(R.drawable.pick_avatar1)
        }


        userName.text = preferences.getUserName()

        editProfile.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.actionUserFragmentToUpdateProfileFragment())
        }
        exit.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.actionUserFragmentToLoginFragment())
        }

        tabPagerAdapter = TabPagerAdapter(requireParentFragment())
        viewPager.adapter = tabPagerAdapter


        val tabTitles = listOf("Watch List", "History")
        val tabIcons = listOf(R.drawable.ic_wishlist, R.drawable.ic_history)

        for (i in tabTitles.indices) {
            val tab = tabLayout.newTab().setText(tabTitles[i]).setIcon(tabIcons[i])
            tabLayout.addTab(tab)
        }


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { viewPager.currentItem = it.position }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

}

