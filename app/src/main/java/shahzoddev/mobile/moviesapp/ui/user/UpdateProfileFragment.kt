package shahzoddev.mobile.moviesapp.ui.user

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentUpdateprofileBinding
import shahzoddev.mobile.moviesapp.databinding.UpdateDataPicksDialogBinding
import shahzoddev.mobile.moviesapp.util.BaseFragment
import androidx.core.graphics.drawable.toDrawable
import shahzoddev.mobile.moviesapp.PreferencesHelper

class UpdateProfileFragment : BaseFragment<FragmentUpdateprofileBinding>(
    FragmentUpdateprofileBinding::inflate) {

    private lateinit var preferences: PreferencesHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = PreferencesHelper(requireContext())



      initUi()


    }

    private fun initUi() = with(binding){



        back.setOnClickListener {
            findNavController().popBackStack()
        }

        deleteAccount.setOnClickListener {
            preferences.clearAll()
            findNavController().navigate(UpdateProfileFragmentDirections.actionUpdateProfileFragmentToLoginFragment())
        }
        userAvatar.setOnClickListener {
            val dialogViewBinding = UpdateDataPicksDialogBinding.inflate(layoutInflater)

            val dialog = AlertDialog.Builder(requireContext())
                .setView(dialogViewBinding.root)
                .create()

            val window = dialog.window
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            window?.setGravity(Gravity.BOTTOM)

            val params = window?.attributes
            params?.gravity = Gravity.BOTTOM
            params?.y = 17
            window?.attributes = params


            val cardList = listOf(
                dialogViewBinding.card1 to dialogViewBinding.image1,
                dialogViewBinding.card2 to dialogViewBinding.image2,
                dialogViewBinding.card3 to dialogViewBinding.image3,
                dialogViewBinding.card4 to dialogViewBinding.image4,
                dialogViewBinding.card5 to dialogViewBinding.image5,
                dialogViewBinding.card6 to dialogViewBinding.image6,
                dialogViewBinding.card7 to dialogViewBinding.image7,
                dialogViewBinding.card8 to dialogViewBinding.image8,
                dialogViewBinding.card9 to dialogViewBinding.image9,
            )

            cardList.forEach { (card, imageView) ->
                card.setOnClickListener {

                    cardList.forEach { (c, _) -> c.isSelected = false }


                    card.isSelected = true

                    userAvatar.setImageDrawable(imageView.drawable)


                    dialog.dismiss()
                }
            }


            dialog.show()
        }
    }


}