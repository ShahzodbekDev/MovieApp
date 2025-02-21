package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingFifthBinding


class OnBoardingFifthFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingFifthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingFifthBinding.inflate(inflater)
        return binding.root
    }

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