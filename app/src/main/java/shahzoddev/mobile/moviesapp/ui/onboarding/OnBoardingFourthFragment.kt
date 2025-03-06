package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingFourthBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment
import shahzoddev.mobile.moviesapp.util.finishActivity


class OnBoardingFourthFragment : BaseFragment<FragmentOnboardingFourthBinding>(FragmentOnboardingFourthBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener {
            findNavController().navigate(OnBoardingFourthFragmentDirections.actionOnBoardingFourthFragmentToOnBoardingFifthFragment())
        }

        binding.back.setOnClickListener {
            finishActivity()
        }
    }

}