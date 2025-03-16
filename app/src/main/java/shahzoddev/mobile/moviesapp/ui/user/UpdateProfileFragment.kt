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
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.util.finishActivity

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
            finishActivity()
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
                dialogViewBinding.dcard1 to dialogViewBinding.dimage1,
                dialogViewBinding.dcard2 to dialogViewBinding.dimage2,
                dialogViewBinding.dcard3 to dialogViewBinding.dimage3,
                dialogViewBinding.dcard4 to dialogViewBinding.dimage4,
                dialogViewBinding.dcard5 to dialogViewBinding.dimage5,
                dialogViewBinding.dcard6 to dialogViewBinding.dimage6,
                dialogViewBinding.dcard7 to dialogViewBinding.dimage7,
                dialogViewBinding.dcard8 to dialogViewBinding.dimage8,
                dialogViewBinding.dcard9 to dialogViewBinding.dimage9,
            )

            cardList.forEach { (card, imageView) ->
                card.setOnClickListener {


                    cardList.forEach { (c, _) -> c.isSelected = false }


                    card.isSelected = true

                    val selectedCardId = when (card.id) {
                        R.id.dcard1 -> 1
                        R.id.dcard2 -> 2
                        R.id.dcard3 -> 3
                        R.id.dcard4 -> 4
                        R.id.dcard5 -> 5
                        R.id.dcard6 -> 6
                        R.id.dcard7 -> 7
                        R.id.dcard8 -> 8
                        R.id.dcard9 -> 9
                        else -> 1
                    }
                        preferences.setPickAvatar(selectedCardId)




                    userAvatar.setImageDrawable(imageView.drawable)

                    dialog.dismiss()
                }
            }

            dialog.show()
        }

        val imageList = listOf(
            R.drawable.pick_avatar1,
            R.drawable.pick_avatar2,
            R.drawable.pick_avatar3,
            R.drawable.pick_avatar4,
            R.drawable.pick_avatar5,
            R.drawable.pick_avatar6,
            R.drawable.pick_avatar7,
            R.drawable.pick_avatar8,
            R.drawable.pick_avatar9,
        )

        val selectedAvatarId = preferences.getPickAvatar()

        if (selectedAvatarId in imageList.indices) {
            userAvatar.setImageResource(imageList[selectedAvatarId])
        } else {
            userAvatar.setImageResource(R.drawable.pick_avatar1)
        }

    }
}