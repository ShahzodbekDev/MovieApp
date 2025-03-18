package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingThirdBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment
import shahzoddev.mobile.moviesapp.util.finishActivity


class OnBoardingThirdFragment :
    BaseFragment<FragmentOnboardingThirdBinding>(FragmentOnboardingThirdBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.next.setOnClickListener {
            findNavController().navigate(OnBoardingThirdFragmentDirections.actionOnBoardingThirdFragmentToOnBoardingFourthFragment())
        }

        binding.back.setOnClickListener {
            finishActivity()
        }

    }

}