package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.PreferencesHelper
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingSixthBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment
import shahzoddev.mobile.moviesapp.util.finishActivity


class OnBoardingSixthFragment :
    BaseFragment<FragmentOnboardingSixthBinding>(FragmentOnboardingSixthBinding::inflate) {

    private lateinit var preferencesHelper: PreferencesHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val preferences = PreferencesHelper(requireContext())



        binding.finish.setOnClickListener {

            preferences.setOnBoardingCompleted(true)
            findNavController().navigate(OnBoardingSixthFragmentDirections.actionOnBoardingSixthFragmentToLoginFragment())
        }

        binding.back.setOnClickListener {
            finishActivity()
        }

    }


}