package shahzoddev.mobile.moviesapp.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentRegisterBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
    }

}