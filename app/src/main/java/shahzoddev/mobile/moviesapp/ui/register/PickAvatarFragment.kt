package shahzoddev.mobile.moviesapp.ui.register

import android.os.Bundle
import android.view.View
import shahzoddev.mobile.moviesapp.databinding.FragmentPickavatarBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment


class PickAvatarFragment : BaseFragment<FragmentPickavatarBinding>(FragmentPickavatarBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            card.setOnClickListener { it ->

                cardList.forEach { it.isSelected = false }

                it.isSelected = true
            }
        }


    }

}