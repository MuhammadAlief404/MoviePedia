package com.quantumhiggs.moviepedia.tvShow

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
import com.quantumhiggs.moviepedia.databinding.FragmentTvShowBinding
import com.quantumhiggs.moviepedia.tvShow.detail.DetailTvShowFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private val viewModel: TvShowViewModel by viewModels()

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShowAdapter = TvShowListAdapter()
        tvShowAdapter.onItemClick = { selectedData ->
            val bundle = bundleOf(DetailTvShowFragment.EXTRA_TV_SHOW to selectedData)
            findNavController().navigate(R.id.toDetailTvShow, bundle)
        }

        viewModel.tvShowList.observe(viewLifecycleOwner, { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        tvShowAdapter.setData(tvShow.data)
                        binding.progressBar.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            context,
                            tvShow.message ?: "Oops.. something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
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