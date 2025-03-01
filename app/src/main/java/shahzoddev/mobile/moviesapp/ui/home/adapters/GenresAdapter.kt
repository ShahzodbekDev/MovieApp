package shahzoddev.mobile.moviesapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shahzoddev.mobile.moviesapp.api.model.Genre
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.databinding.ItemGenresBinding

class GenresAdapter(
    private var genres: List<Genre>
) : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {
    inner class GenresViewHolder(
       private val binding: ItemGenresBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genres: Genre) = with(binding) {

            genresName.text = genres.name


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GenresViewHolder (
        ItemGenresBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )



    override fun getItemCount() = genres.size

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    fun updateData(newList: List<Genre>) {
        genres = newList
        notifyDataSetChanged()
    }
}