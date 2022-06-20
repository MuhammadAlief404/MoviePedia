package com.quantumhiggs.moviepedia.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.quantumhiggs.moviepedia.R
import com.quantumhiggs.moviepedia.core.data.Resource
import com.quantumhiggs.moviepedia.databinding.FragmentMovieBinding
import com.quantumhiggs.moviepedia.movie.detail.DetailMovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieListAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val bundle = bundleOf(DetailMovieFragment.EXTRA_MOVIE to selectedData)
            findNavController().navigate(R.id.toDetailMovie, bundle)
        }

        viewModel.movieList.observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        movieAdapter.setData(movie.data)
                        binding.progressBar.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            context,
                            movie.message ?: "Oops.. something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
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