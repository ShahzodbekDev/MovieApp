package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingSecondBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class OnBoardingSecondFragment : BaseFragment<FragmentOnboardingSecondBinding>(FragmentOnboardingSecondBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener {

            findNavController().navigate(OnBoardingSecondFragmentDirections.actionOnBoardingSecondFragmentToOnBoardingThirdFragment())

        }
    }


}