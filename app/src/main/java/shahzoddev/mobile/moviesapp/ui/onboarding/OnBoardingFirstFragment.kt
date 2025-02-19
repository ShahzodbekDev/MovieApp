package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingFirstBinding


class OnBoardingFirstFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonExploreNow.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFirstFragment_to_onBoardingSecondFragment)
        }

    }
}