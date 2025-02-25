package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingFirstBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment
import shahzoddev.mobile.moviesapp.util.clearLightStatusBar


class OnBoardingFirstFragment : BaseFragment<FragmentOnboardingFirstBinding>(FragmentOnboardingFirstBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clearLightStatusBar()


        binding.buttonExploreNow.setOnClickListener {
            findNavController().navigate(OnBoardingFirstFragmentDirections.actionOnBoardingFirstFragmentToOnBoardingSecondFragment())
        }

    }
}