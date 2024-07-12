package com.quantumhiggs.moviepedia.movie.detail

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
import com.quantumhiggs.moviepedia.core.domain.model.Movies
import com.quantumhiggs.moviepedia.databinding.FragmentDetailMovieBinding
import com.quantumhiggs.moviepedia.util.AppUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailMovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailMovie = arguments?.get(EXTRA_MOVIE) as Movies

        with(binding) {
            tvDetailRating2Mv.text = detailMovie.movieRating
            tvDetailReleaseDate2Mv.text = detailMovie.movieReleaseDate
            tvDetailLanguage2Mv.text = detailMovie.movieLanguage
            tvDetailDescMv.text = detailMovie.movieDesc
            tvDetailTitleMv.text = detailMovie.movieName

            Glide.with(context!!)
                .asBitmap()
                .load(BuildConfig.BASE_IMG + detailMovie.movieImage)
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
                            binding.btnShare.backgroundTintList =
                                ColorStateList.valueOf(Color.WHITE)
                            binding.btnFavorite.backgroundTintList =
                                ColorStateList.valueOf(Color.WHITE)
                            binding.btnBack.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
                        } else {
                            binding.btnShare.backgroundTintList =
                                ColorStateList.valueOf(Color.BLACK)
                            binding.btnFavorite.backgroundTintList =
                                ColorStateList.valueOf(Color.BLACK)
                            binding.btnBack.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
                        }
                        binding.imgDetailPosterMv.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // handle cleanup here
                    }
                })

            btnShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Hey, Check this movie, ${detailMovie.movieName}")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }

            var favoriteState = detailMovie.isFavorite
            setFavoriteState(favoriteState)
            binding.btnFavorite.setOnClickListener {
                favoriteState = !favoriteState
                viewModel.setFavorite(detailMovie, favoriteState)
                setFavoriteState(favoriteState)
            }
        }
    }

    private fun setFavoriteState(isFavorite: Boolean) {
        if (isFavorite) {
            binding.btnFavorite.background = ContextCompat.getDrawable(
                context!!,
                R.drawable.ic_favorite_fill
            )
        } else {
            binding.btnFavorite.background = ContextCompat.getDrawable(
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
        const val EXTRA_MOVIE = "extra_movie"
    }

}
