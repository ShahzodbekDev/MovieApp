package shahzoddev.mobile.moviesapp.ui.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.databinding.ItemSearchResultBinding

class MovieDiffUtils(): DiffUtil.ItemCallback<MovieListResult>(){
    override fun areItemsTheSame(
        oldItem: MovieListResult,
        newItem: MovieListResult
    ): Boolean {
        return  oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MovieListResult,
        newItem: MovieListResult
    ): Boolean {
        return  oldItem == newItem
    }

}

class SearchAdapter(
    private val onClick: (MovieListResult) -> Unit
) : PagingDataAdapter< MovieListResult,SearchAdapter.ViewHolder>(MovieDiffUtils()) {

    inner class ViewHolder(val binding: ItemSearchResultBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(result: MovieListResult?) = with(binding) {
            result?.let { movie ->
                Glide.with(root).load(movie.poster).into(searchResultImage)
                searchResultRating.text =
                    root.context.getString(R.string.search_result_rating, movie.imdb_rating)
                root.setOnClickListener {
                    onClick(movie)
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder (
        ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent,false)
    )

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
       holder.bind(getItem(position))
    }


}