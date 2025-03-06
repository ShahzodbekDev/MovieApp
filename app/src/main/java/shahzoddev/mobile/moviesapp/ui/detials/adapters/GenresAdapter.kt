package shahzoddev.mobile.moviesapp.ui.detials.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shahzoddev.mobile.moviesapp.databinding.ItemDetialsGenresBinding


class GenresAdapter(private var genres: List<String>) :
    RecyclerView.Adapter<GenresAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(val binding: ItemDetialsGenresBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = ItemDetialsGenresBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.detialsGenresName.text = genres[position]
    }

    override fun getItemCount(): Int = genres.size
}

