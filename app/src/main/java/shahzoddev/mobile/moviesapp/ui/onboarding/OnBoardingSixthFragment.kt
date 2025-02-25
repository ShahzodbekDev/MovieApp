package shahzoddev.mobile.moviesapp.ui.onboarding

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import shahzoddev.mobile.moviesapp.databinding.FragmentOnboardingSixthBinding
import shahzoddev.mobile.moviesapp.sqlitedatabase.SQLiteHelper
import shahzoddev.mobile.moviesapp.util.BaseFragment


class OnBoardingSixthFragment : BaseFragment<FragmentOnboardingSixthBinding>(FragmentOnboardingSixthBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() = with(binding) {


        finish.setOnClickListener {
            updateOnBoarding()
            findNavController().navigate(OnBoardingSixthFragmentDirections.actionOnBoardingSixthFragmentToLoginFragment())
        }

        back.setOnClickListener {
            requireActivity().finish()
        }

    }
    private fun updateOnBoarding() {
        val dbHelper = SQLiteHelper(requireContext())
        val db = dbHelper.writableDatabase

        val contentValues = ContentValues().apply {
            put("onBoarding", 1)
        }

        db.update("users", contentValues, null, null)
        db.close()
    }


}