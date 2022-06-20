package com.quantumhiggs.moviepedia.tvShow.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.quantumhiggs.moviepedia.R
import com.quantumhiggs.moviepedia.core.BuildConfig
import com.quantumhiggs.moviepedia.core.domain.model.TvShows
import com.quantumhiggs.moviepedia.databinding.FragmentDetailTvShowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvShowFragment : Fragment() {

    private var _binding: FragmentDetailTvShowBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailTvShowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailTvShowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailTvShow = arguments?.get(EXTRA_TV_SHOW) as TvShows


        with(binding) {
            tvDetailTitleTv.text = detailTvShow.tvShowName
            tvDetailReleaseDate2Tv.text = detailTvShow.tvShowReleaseDate
            tvDetailLanguage2Tv.text = detailTvShow.tvShowReleaseDate
            tvDetailDescTv.text = detailTvShow.tvShowDesc
            tvDetailRating2Tv.text = detailTvShow.tvShowRating

            Glide.with(context!!)
                .load(BuildConfig.BASE_IMG + detailTvShow.tvShowImage)
                .into(imgDetailPosterTv)

            btnShareTv.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Hey, Check this Show, ${detailTvShow.tvShowName}")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

            binding.btnBackTv.setOnClickListener {
                findNavController().navigateUp()
            }

            var favoriteState = detailTvShow.isFavorite
            setFavoriteState(detailTvShow.isFavorite)
            binding.btnFavoriteTv.setOnClickListener {
                favoriteState = !favoriteState
                viewModel.setFavorite(detailTvShow, favoriteState)
                setFavoriteState(favoriteState)
            }
        }
    }

    private fun setFavoriteState(isFavorite: Boolean) {
        if (isFavorite) {
            binding.btnFavoriteTv.background = ContextCompat.getDrawable(
                context!!,
                R.drawable.ic_favorite_fill
            )
        } else {
            binding.btnFavoriteTv.background = ContextCompat.getDrawable(
                context!!,
                R.drawable.ic_favorite_blank
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_TV_SHOW = "extra_tvShow"
    }
}
