package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingSecondBinding


class OnBoardingSecondFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentOnboardingSecondBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener {

            findNavController().navigate(R.id.action_onBoardingSecondFragment_to_onBoardingThirdFragment)

        }
    }


}