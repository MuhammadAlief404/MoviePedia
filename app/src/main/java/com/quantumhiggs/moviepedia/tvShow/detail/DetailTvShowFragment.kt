package com.quantumhiggs.moviepedia.tvShow.detail

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.quantumhiggs.moviepedia.R
import com.quantumhiggs.moviepedia.core.BuildConfig
import com.quantumhiggs.moviepedia.core.domain.model.TvShows
import com.quantumhiggs.moviepedia.databinding.FragmentDetailTvShowBinding
import com.quantumhiggs.moviepedia.util.AppUtil
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
                .asBitmap()
                .load(BuildConfig.BASE_IMG + detailTvShow.tvShowImage)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        //create a new bitmap with same image but cropped and get the top area only with height 80
                        val topAreaBitmap = Bitmap.createBitmap(resource, 0, 0, resource.width, 80)
                        val palette = Palette.from(topAreaBitmap).generate()
                        val dominantColor = palette.getDominantColor(Color.WHITE)

                        if (AppUtil.isBackgroundDark(dominantColor)) {
                            binding.btnShareTv.backgroundTintList =
                                ColorStateList.valueOf(Color.WHITE)
                            binding.btnFavoriteTv.backgroundTintList =
                                ColorStateList.valueOf(Color.WHITE)
                            binding.btnBackTv.backgroundTintList =
                                ColorStateList.valueOf(Color.WHITE)
                        } else {
                            binding.btnShareTv.backgroundTintList =
                                ColorStateList.valueOf(Color.BLACK)
                            binding.btnFavoriteTv.backgroundTintList =
                                ColorStateList.valueOf(Color.BLACK)
                            binding.btnBackTv.backgroundTintList =
                                ColorStateList.valueOf(Color.BLACK)
                        }
                        binding.imgDetailPosterTv.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // handle cleanup here
                    }
                })

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
            setFavoriteState(favoriteState)
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
