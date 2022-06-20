package com.quantumhiggs.moviepedia.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.quantumhiggs.moviepedia.R
import com.quantumhiggs.moviepedia.di.FavoriteModuleDependencies
import com.quantumhiggs.moviepedia.favorite.ViewModelFactory
import com.quantumhiggs.moviepedia.favorite.databinding.FragmentFavoriteMovieBinding
import com.quantumhiggs.moviepedia.favorite.di.DaggerFavoriteComponent
import com.quantumhiggs.moviepedia.movie.detail.DetailMovieFragment
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteMovieFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteMovieViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFavoriteComponent.builder()
            .context(context!!)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    activity?.applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = FavoriteMovieListAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val bundle = bundleOf(DetailMovieFragment.EXTRA_MOVIE to selectedData)
            findNavController().navigate(R.id.favoriteMovieToDetail, bundle)
        }

        viewModel.favoriteMovie.observe(viewLifecycleOwner, {
            movieAdapter.setData(it)
        })
        with(binding.rvListMovie) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}