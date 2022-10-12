package com.mawinda.themoviedb.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.mawinda.data.local.entities.Movie
import com.mawinda.themoviedb.R
import com.mawinda.themoviedb.adapters.PagingAdapter
import com.mawinda.themoviedb.databinding.FragmentHomeBinding
import com.mawinda.themoviedb.databinding.MovieItemListBinding
import com.mawinda.themoviedb.utils.toActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!


    private val fragToActivity by toActivity()

    //Adapter
    private val adapter: PagingAdapter<Movie, MovieItemListBinding> by lazy {
        //Movie Item Comparator
        val comparator = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }


        PagingAdapter<Movie, MovieItemListBinding>(comparator).onCreate { parent ->
            //Binding layout
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.movie_item_list,
                parent,
                false
            )
        }.onBind { item ->
            this.movie = item
            this.executePendingBindings()
        }.apply {

            //Item Click Listener
            this.onItemClicked { movie: Movie ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                        movie = movie
                    )
                )
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.apply {
            this.viewModel = homeModel
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragToActivity.setTitle("Trending")
        binding.container.adapter = adapter
        lifecycleScope.launchWhenResumed {
            homeModel.movies.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenResumed {
            adapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        homeModel.setError(error = null)
                    }
                    LoadState.Loading -> Timber.i("Loading")
                    is LoadState.Error -> {
                        val error = (it.refresh as LoadState.Error).error.message
                        val mError =
                            Gson().fromJson(error, com.mawinda.data.domain.model.Error::class.java)
                        if (mError.success.not()) {
                            Snackbar.make(binding.root, mError.statusMessage, Snackbar.LENGTH_LONG)
                                .show()
                            homeModel.setError(mError)
                        }

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}