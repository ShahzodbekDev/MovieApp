package shahzoddev.mobile.moviesapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shahzoddev.mobile.moviesapp.R
import shahzoddev.mobile.moviesapp.api.model.MovieListResult
import shahzoddev.mobile.moviesapp.databinding.ItemBannerBinding
import shahzoddev.mobile.moviesapp.domain.Banner

class BannerAdapter(
    private var banner: List<MovieListResult>,
) : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieListResult) = with(binding) {
            movie.let {
                Glide.with(root).load(movie.poster).into(image)
                rating.text =
                    root.context.getString(R.string.movie_card_rating, movie.imdb_rating)



            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(banner[position])

    override fun getItemCount() = banner.size

    fun updateData(newList: List<MovieListResult>) {
        banner = newList
        notifyDataSetChanged()
    }


}






