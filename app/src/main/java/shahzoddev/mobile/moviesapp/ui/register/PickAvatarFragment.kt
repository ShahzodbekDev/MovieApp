package shahzoddev.mobile.moviesapp.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.databinding.FragmentPickavatarBinding


class PickAvatarFragment : Fragment() {

    private lateinit var binding: FragmentPickavatarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPickavatarBinding.inflate(inflater)
        return binding.root
    }


}