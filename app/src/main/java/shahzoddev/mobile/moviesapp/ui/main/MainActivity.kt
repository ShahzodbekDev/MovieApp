package shahzoddev.mobile.moviesapp.ui.main

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController get() = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigation

        ViewCompat.setOnApplyWindowInsetsListener(bottomNavigationView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = systemBars.bottom + 20
            }
            insets
        }


        initUI()


    }

    private fun initUI() = with(binding) {

        bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigation.isVisible = listOf(
                R.id.splashFragment,
                R.id.onBoardingFirstFragment,
                R.id.onBoardingSecondFragment,
                R.id.onBoardingThirdFragment,
                R.id.onBoardingFourthFragment,
                R.id.onBoardingFifthFragment,
                R.id.onBoardingSixthFragment,
                R.id.loginFragment,
                R.id.registerFragment,
                R.id.pickAvatarFragment,
                R.id.forgotPasswprdFragment,
                R.id.detialsFragment,
                R.id.updateProfileFragment
            ).all { it != destination.id }
        }


    }
}