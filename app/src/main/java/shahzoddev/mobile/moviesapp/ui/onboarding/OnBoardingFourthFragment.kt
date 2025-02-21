package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingFourthBinding


class OnBoardingFourthFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingFourthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingFourthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener {
            findNavController().navigate(OnBoardingFourthFragmentDirections.actionOnBoardingFourthFragmentToOnBoardingFifthFragment())
        }

        binding.back.setOnClickListener {
            requireActivity().finish()
        }
    }

}