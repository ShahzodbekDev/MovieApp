package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingSixthBinding


class OnBoardingSixthFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingSixthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingSixthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.finish.setOnClickListener {
            findNavController().navigate(OnBoardingSixthFragmentDirections.actionOnBoardingSixthFragmentToLoginFragment())
        }

        binding.back.setOnClickListener {
            requireActivity().finish()
        }
    }

}