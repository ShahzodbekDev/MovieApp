package shahzoddev.mobile.moviesapp.ui.register

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.PreferencesHelper
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.databinding.FragmentRegisterBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    private lateinit var preferences: PreferencesHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = PreferencesHelper(requireContext())


        initUi()


    }

    private fun initUi() = with(binding) {


        val nameText = name.text
        val emailText = email.text
        val passwordText = password.text
        val confirmPasswordText = confirmPassword.text
        val phoneText = phone.text

        createAccount.setOnClickListener {

            if (passwordText.toString() == confirmPasswordText.toString()) {
                preferences.setUserName(nameText.toString())
                preferences.setEmail(emailText.toString())
                preferences.setPassword(passwordText.toString())
                preferences.setPhone(phoneText.toString())

                preferences.setOnLoginCompleted(true)
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToPickAvatarFragment())
            } else {
                password.setBackgroundResource(R.drawable.input_field_erorr_background)
                confirmPassword.setBackgroundResource(R.drawable.input_field_erorr_background)
            }



            Log.d("TAG", "$nameText ,$emailText, $passwordText,$confirmPasswordText, $phoneText")

        }




        toLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
        back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}