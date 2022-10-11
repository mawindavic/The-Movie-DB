package com.mawinda.themoviedb.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.mawinda.themoviedb.R
import com.mawinda.themoviedb.databinding.FragmentDetailsBinding
import com.mawinda.themoviedb.utils.toActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val detailsViewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    private val fragToActivity by toActivity()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, container, false)
        return binding.apply {
            this.viewModel = detailsViewModel
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsViewModel.setMovie(args.movie)

        lifecycleScope.launchWhenResumed {
            detailsViewModel.movie.observe(viewLifecycleOwner) {
                it?.let { movie ->
                    fragToActivity.setTitle(movie.title ?: "")
                    Timber.i("Movie: $movie")
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}