package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingFirstBinding
import shahzoddev.mobile.moviesapp.util.clearLightStatusBar


class OnBoardingFirstFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clearLightStatusBar()


        binding.buttonExploreNow.setOnClickListener {
            findNavController().navigate(OnBoardingFirstFragmentDirections.actionOnBoardingFirstFragmentToOnBoardingSecondFragment())
        }

    }
}