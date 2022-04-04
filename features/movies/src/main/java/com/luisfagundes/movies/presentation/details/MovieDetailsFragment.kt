package com.luisfagundes.movies.presentation.details

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.domain.model.Actor
import com.luisfagundes.domain.model.MovieDetails
import com.luisfagundes.extensions.hideVisibility
import com.luisfagundes.extensions.loadPoster
import com.luisfagundes.extensions.observe
import com.luisfagundes.extensions.showVisibility
import com.luisfagundes.movies.R
import com.luisfagundes.movies.databinding.FragmentMovieDetailsBinding
import com.luisfagundes.movies.presentation.details.adapter.CastAdapter
import com.luisfagundes.movies.utils.loadBackdrop
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(
    successViewId = R.id.success_movie_details_container,
    loadingViewId = R.id.loading_movie_details_container,
    errorViewId = R.id.error_movie_details_container,
) {

    private val viewModel: MovieDetailsViewModel by viewModel()
    private val castAdapter: CastAdapter by inject()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onBind() = FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun FragmentMovieDetailsBinding.onViewCreated() {
        setupUpActionButton()
        setupCastRecyclerView()
        setupObservers()

        viewModel.dispatchViewAction(MovieDetailsViewAction.FetchMovieDetails(args.movieId))
    }

    private fun setupCastRecyclerView() = with(binding.rvCast) {
        val layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        setHasFixedSize(true)
        this.layoutManager = layoutManager
        this.adapter = castAdapter
    }

    private fun setupObservers() {
        observeMovieDetails()
        observeCast()
    }

    private fun observeMovieDetails() {
        observe(viewModel.viewState.movie) {
            when (this) {
                is MovieDetailsViewState.State.Success -> showMovieSuccess()
                is MovieDetailsViewState.State.Loading -> super.showLoading()
                is MovieDetailsViewState.State.Error -> super.showError()
                else -> super.showError()
            }
        }
    }

    private fun observeCast() {
        observe(viewModel.viewState.cast) {
            when (this) {
                is MovieDetailsViewState.State.Success -> showCastSuccess()
                is MovieDetailsViewState.State.Loading -> showCastProgressBar()
                is MovieDetailsViewState.State.Error -> showCastError()
                else -> showCastError()
            }
        }
    }

    private fun MovieDetailsViewState.State.Success<MovieDetails>.showMovieSuccess() {
        super.showSuccess()
        this.data.setupMovieDetails()
    }


    private fun MovieDetailsViewState.State.Success<List<Actor>>.showCastSuccess() {
        binding.pbCast.hideVisibility()
        binding.rvCast.showVisibility()
        binding.castErrorContainer.rootContainer.hideVisibility()
        castAdapter.updateList(this.data)
    }

    private fun showCastProgressBar() = with(binding) {
        pbCast.showVisibility()
        rvCast.hideVisibility()
        binding.castErrorContainer.rootContainer.hideVisibility()
    }

    private fun showCastError() = with(binding) {
        pbCast.hideVisibility()
        rvCast.hideVisibility()
        binding.castErrorContainer.rootContainer.showVisibility()
    }

    private fun MovieDetails.setupMovieDetails() = with(binding) {
        tvTitle.text = title
        tvReleaseDate.text = releaseDate
        imgPoster.imgPoster.loadPoster(posterUrl)
        tvTmdbScore.text = voteAverage.toString()
        tvOverview.text = overview
        imgBackdrop.loadBackdrop(backDropUrl)
    }
}