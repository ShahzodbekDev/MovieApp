package shahzoddev.mobile.moviesapp.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.PreferencesHelper
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.databinding.FragmentLoginBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private lateinit var preferences: PreferencesHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = PreferencesHelper(requireContext())

        initUi()

    }

    private fun initUi() = with(binding) {
        val emailText = email.text
        val passwordText = password.text


        login.setOnClickListener {
            if (emailText.toString() == preferences.getEmail() && passwordText.toString() == preferences.getPassword()) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                preferences.setOnLoginCompleted(true)
            } else {
                email.setBackgroundResource(R.drawable.input_field_erorr_background)
                password.setBackgroundResource(R.drawable.input_field_erorr_background)
            }
        }



        toRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        forgotPassword.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswprdFragment())
        }
    }

}