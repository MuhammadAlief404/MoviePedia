package com.quantumhiggs.moviepedia.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quantumhiggs.moviepedia.R
import com.quantumhiggs.moviepedia.core.BuildConfig.BASE_IMG
import com.quantumhiggs.moviepedia.core.domain.model.Movies
import com.quantumhiggs.moviepedia.databinding.ItemListMoviesBinding
import java.util.ArrayList

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setData(newListData: List<Movies>?) {
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
        fun bind(data: Movies) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BASE_IMG + data.movieImage)
                    .into(imgItemPoster)
                tvItemTitle.text = data.movieName
                tvItemDesc.text = data.movieDesc
                tvItemRating.text = data.movieRating
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}