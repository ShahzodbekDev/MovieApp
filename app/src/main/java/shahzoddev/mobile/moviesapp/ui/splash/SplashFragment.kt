package shahzoddev.mobile.moviesapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.PreferencesHelper
import shahzoddev.mobile.moviesapp.databinding.FragmentSplashBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private lateinit var preferences: PreferencesHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = PreferencesHelper(requireContext())

        val userName = preferences.getUserName()
        val email = preferences.getEmail()
        val password = preferences.getPassword()
        val phone = preferences.getPhone()
        val pickAvatar = preferences.getPickAvatar()
        val isLogin = preferences.isOnLoginCompleted()

        Log.d("TAG", "$userName ,$email, $password, $phone, $pickAvatar, $isLogin")

        Handler().postDelayed({

            if (preferences.isOnBoardingCompleted() && preferences.isOnLoginCompleted()) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else if (preferences.isOnBoardingCompleted()) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFirstFragment())
            }


        }, 1000)

    }


}