package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingFifthBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class OnBoardingFifthFragment : BaseFragment<FragmentOnboardingFifthBinding>(FragmentOnboardingFifthBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.next.setOnClickListener {
            findNavController().navigate(OnBoardingFifthFragmentDirections.actionOnBoardingFifthFragmentToOnBoardingSixthFragment())
        }


        binding.back.setOnClickListener {
            requireActivity().finish()
        }
    }

}