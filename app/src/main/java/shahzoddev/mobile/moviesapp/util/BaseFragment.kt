package shahzoddev.mobile.moviesapp.util

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import shahzoddev.mobile.moviesapp.api.MoviesRepository

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    var _binding: VB? = null
    val binding get() = _binding ?: throw IllegalStateException("Binding is not available")


    private val handler = Handler(Looper.getMainLooper())
    private val runnables = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container, false)
        return requireNotNull(_binding).root
    }


    protected fun postRunnable(runnable: Runnable, delayMillis: Long) {
        runnables.add(runnable)
        handler.postDelayed(runnable, delayMillis)
    }


    fun clearRunnables() {
        runnables.forEach { handler.removeCallbacks(it) }
        runnables.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearRunnables()
        _binding = null
    }



}

