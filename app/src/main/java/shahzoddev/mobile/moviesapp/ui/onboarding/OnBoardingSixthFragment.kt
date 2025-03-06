package shahzoddev.mobile.moviesapp.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingSixthBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment
import shahzoddev.mobile.moviesapp.util.finishActivity


class OnBoardingSixthFragment :
    BaseFragment<FragmentOnboardingSixthBinding>(FragmentOnboardingSixthBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.finish.setOnClickListener {
            findNavController().navigate(OnBoardingSixthFragmentDirections.actionOnBoardingSixthFragmentToLoginFragment())
        }

        binding.back.setOnClickListener {
            finishActivity()
        }

    }




}