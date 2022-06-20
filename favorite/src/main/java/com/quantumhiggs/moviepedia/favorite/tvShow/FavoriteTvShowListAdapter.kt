package com.quantumhiggs.moviepedia.favorite.tvShow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quantumhiggs.moviepedia.R
import com.quantumhiggs.moviepedia.core.BuildConfig
import com.quantumhiggs.moviepedia.core.domain.model.TvShows
import com.quantumhiggs.moviepedia.databinding.ItemListMoviesBinding
import java.util.*

class FavoriteTvShowListAdapter : RecyclerView.Adapter<FavoriteTvShowListAdapter.ListViewHolder>() {

    private var listData = ArrayList<TvShows>()
    var onItemClick: ((TvShows) -> Unit)? = null

    fun setData(newListData: List<TvShows>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movies, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMoviesBinding.bind(itemView)
        fun bind(data: TvShows) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMG + data.tvShowImage)
                    .into(imgItemPoster)
                tvItemTitle.text = data.tvShowName
                tvItemDesc.text = data.tvShowDesc
                tvItemRating.text = data.tvShowRating
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}