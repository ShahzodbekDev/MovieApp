package shahzoddev.mobile.moviesapp.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentLoginBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toRegister.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.forgotPassword.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswprdFragment())
        }

    }

}