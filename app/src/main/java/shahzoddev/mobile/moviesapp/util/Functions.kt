package shahzoddev.mobile.moviesapp.util

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment


fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

@RequiresApi(Build.VERSION_CODES.M)
fun Fragment.setLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        requireActivity().window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        @Suppress("DEPRECATION")
        requireActivity().window.decorView.systemUiVisibility =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }

    }
}

fun Fragment.finishActivity() {
    requireActivity().finish()
}

fun Fragment.blockOrientation(){
    requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
}

fun Fragment.clearLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        requireActivity().window.insetsController?.setSystemBarsAppearance(
            0,
            APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        requireActivity().window.decorView.systemUiVisibility = 0
    }
}


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.showKeyboard() {
    view?.let { activity?.showKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, 0)
}
