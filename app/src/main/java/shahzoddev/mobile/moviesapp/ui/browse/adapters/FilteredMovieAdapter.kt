package shahzoddev.mobile.moviesapp.ui.browse.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.databinding.ItemFilteredMovieBrowseBinding

class FilteredMovieAdapter(
    private var movies: List<MovieListResult>,
    private val onClick: (MovieListResult) -> Unit
): RecyclerView.Adapter<FilteredMovieAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(private val binding: ItemFilteredMovieBrowseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieListResult) = with(binding) {
            movie.let {
                Glide.with(root).load(movie.poster).into(filterdResultImage)
                filteredResultRating.text =
                    root.context.getString(R.string.filtered_resut_rating, movie.imdb_rating)
                root.setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviesViewHolder (
        ItemFilteredMovieBrowseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FilteredMovieAdapter.MoviesViewHolder, position: Int) =
        holder.bind(movies[position])

    override fun getItemCount() = movies.size

    fun updateData(newList: List<MovieListResult>) {
        movies = newList
        notifyDataSetChanged()
    }

}