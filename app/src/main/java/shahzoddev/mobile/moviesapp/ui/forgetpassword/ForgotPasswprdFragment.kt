package shahzoddev.mobile.moviesapp.ui.forgetpassword

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.PreferencesHelper
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.databinding.FragmentForgotpasswprdBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class ForgotPasswprdFragment :
    BaseFragment<FragmentForgotpasswprdBinding>(FragmentForgotpasswprdBinding::inflate) {

    private lateinit var preferencesHelper: PreferencesHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferencesHelper = PreferencesHelper(requireContext())

        val emailText = binding.email.text

        binding.fogetPasVerifyEmail.setOnClickListener {
            if (emailText.toString() == preferencesHelper.getEmail()) {
                findNavController().navigate(ForgotPasswprdFragmentDirections.actionForgotPasswprdFragmentToHomeFragment())
            } else {
                binding.email.setBackgroundResource(R.drawable.input_field_erorr_background)
            }
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}