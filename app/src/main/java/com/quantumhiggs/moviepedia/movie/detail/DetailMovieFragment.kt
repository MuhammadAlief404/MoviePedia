package com.quantumhiggs.moviepedia.movie.detail

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
import com.quantumhiggs.moviepedia.core.domain.model.Movies
import com.quantumhiggs.moviepedia.databinding.FragmentDetailMovieBinding
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
                .load(BuildConfig.BASE_IMG + detailMovie.movieImage)
                .into(imgDetailPosterMv)

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
            setFavoriteState(detailMovie.isFavorite)
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
