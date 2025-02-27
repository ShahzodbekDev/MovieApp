package shahzoddev.mobile.moviesapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentSplashBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Handler().postDelayed({

                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFirstFragment())

        }, 1000)

    }








}