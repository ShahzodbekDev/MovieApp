package shahzoddev.mobile.moviesapp.ui.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.PreferencesHelper
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.databinding.FragmentPickavatarBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class PickAvatarFragment :
    BaseFragment<FragmentPickavatarBinding>(FragmentPickavatarBinding::inflate) {

    private lateinit var preferencesHelper: PreferencesHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferencesHelper = PreferencesHelper(requireContext())

        initUI()
    }

    private fun initUI() = with(binding) {

        val cardList = listOf(
            card1,
            card2,
            card3,
            card4,
            card5,
            card6,
            card7,
            card8,
            card9
        )


        cardList.forEach { card ->
            card.setOnClickListener {

                cardList.forEach { it.isSelected = false }


                card.isSelected = true
                val selectedCardId = when (card.id) {
                    R.id.card1 -> 1
                    R.id.card2 -> 2
                    R.id.card3 -> 3
                    R.id.card4 -> 4
                    R.id.card5 -> 5
                    R.id.card6 -> 6
                    R.id.card7 -> 7
                    R.id.card8 -> 8
                    R.id.card9 -> 9
                    else -> 1
                }

                    preferencesHelper.setPickAvatar(selectedCardId)

            }
        }

        verifyEmail.setOnClickListener {
            findNavController().navigate(PickAvatarFragmentDirections.actionPickAvatarFragmentToHomeFragment())
        }
    }

}