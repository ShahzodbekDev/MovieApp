package shahzoddev.mobile.moviesapp.ui.detials.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.databinding.ItemDetialsMoviesListBinding
import shahzoddev.mobile.moviesapp.databinding.ItemHomeMoviesBinding

class MovieListAdapter(
    private var movies: List<MovieListResult>,
    private val onClick: (MovieListResult) -> Unit
): RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(private val binding: ItemDetialsMoviesListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieListResult) = with(binding) {
            movie.let {
                Glide.with(root).load(movie.poster).into(image)
                homeMoviesRating.text =
                    root.context.getString(R.string.banner_card_rating, movie.imdb_rating)
                root.setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviesViewHolder (
            ItemDetialsMoviesListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieListAdapter.MoviesViewHolder, position: Int) =
        holder.bind(movies[position])

    override fun getItemCount() = movies.size

    fun updateData(newList: List<MovieListResult>) {
        movies = newList
        notifyDataSetChanged()
    }

}