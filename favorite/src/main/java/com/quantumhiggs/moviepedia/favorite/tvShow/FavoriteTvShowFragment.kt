package com.quantumhiggs.moviepedia.favorite.tvShow

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
import com.quantumhiggs.moviepedia.favorite.databinding.FragmentFavoriteTvShowBinding
import com.quantumhiggs.moviepedia.favorite.di.DaggerFavoriteComponent
import com.quantumhiggs.moviepedia.tvShow.detail.DetailTvShowFragment
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteTvShowFragment : Fragment() {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteTvShowViewModel by viewModels {
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
        _binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShowAdapter = FavoriteTvShowListAdapter()
        tvShowAdapter.onItemClick = { selectedData ->
            val bundle = bundleOf(DetailTvShowFragment.EXTRA_TV_SHOW to selectedData)
            findNavController().navigate(R.id.favoriteTvShowToDetail, bundle)
        }

        viewModel.favoriteTvShow.observe(viewLifecycleOwner, {
            tvShowAdapter.setData(it)
        })
        with(binding.rvListTv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = tvShowAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}