package shahzoddev.mobile.moviesapp.ui.browse.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.api.model.Genre
import shahzoddev.mobile.moviesapp.databinding.ItemBrowseGenresBinding

class BrowseGenresAdapter(
    private var genres: List<Genre>, val onGenreClick: (genre: Genre) -> Unit
) : RecyclerView.Adapter<BrowseGenresAdapter.ViewHolder>() {

    private var selectedGenre: Genre? = null


    fun setSelectedGenre(genre: Genre) {
        selectedGenre = genre
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemBrowseGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) = with(binding) {
            genresName.text = genre.name
            root.setOnClickListener {
                onGenreClick(genre)
            }

            if (selectedGenre == genre) {
                root.background = ContextCompat.getDrawable(
                    root.context,
                    R.drawable.browse_selected_genres_background
                )
                genresName.setTextColor(ContextCompat.getColor(root.context, R.color.black))
            } else {
                root.background =
                    ContextCompat.getDrawable(root.context, R.drawable.browse_genres_background)
                genresName.setTextColor(ContextCompat.getColor(root.context, R.color.app_color))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBrowseGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int = genres.size

    fun updateData(newGenres: List<Genre>) { // 📌 `List<String>` emas, `List<Genre>`
        genres = newGenres
        notifyDataSetChanged()
    }
}
