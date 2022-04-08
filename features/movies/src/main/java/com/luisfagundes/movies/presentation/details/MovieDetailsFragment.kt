package com.luisfagundes.movies.presentation.details

import androidx.navigation.fragment.navArgs
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.domain.model.MovieDetails
import com.luisfagundes.extensions.loadPoster
import com.luisfagundes.extensions.observe
import com.luisfagundes.extensions.setupDefaultConfig
import com.luisfagundes.movies.R
import com.luisfagundes.movies.databinding.FragmentMovieDetailsBinding
import com.luisfagundes.movies.presentation.details.adapter.CastAdapter
import com.luisfagundes.movies.presentation.details.adapter.TrailerAdapter
import com.luisfagundes.movies.utils.extensions.formatRating
import com.luisfagundes.movies.utils.extensions.loadBackdrop
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(
    successViewId = R.id.success_movie_details_container,
    loadingViewId = R.id.loading_movie_details_container,
    errorViewId = R.id.error_movie_details_container,
) {

    private val viewModel: MovieDetailsViewModel by viewModel()
    private val castAdapter: CastAdapter by inject()
    private val trailerAdapter: TrailerAdapter by inject()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onBind() = FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun FragmentMovieDetailsBinding.onViewCreated() {
        setupViews()
        setupUpActionButton()
        setupObservers()

        viewModel.dispatchViewAction(MovieDetailsViewAction.FetchMovieDetails(args.movieId))
    }

    private fun setupViews() {
        setupCastRecyclerView()
        //setupTrailersRecyclerView()
    }

    private fun setupCastRecyclerView() = with(binding.rvCast) {
        setupDefaultConfig()
        this.adapter = castAdapter
    }

//    private fun setupTrailersRecyclerView() = with(binding.rvTrailers) {
//        setupDefaultConfig()
//        this.adapter = trailerAdapter
//    }

    private fun setupObservers() {
        observe(viewModel.viewState.movie) {
            when (this) {
                is MovieDetailsViewState.State.Success -> showSuccess()
                is MovieDetailsViewState.State.Loading -> showLoading()
                is MovieDetailsViewState.State.Error -> showError()
                else -> showError()
            }
        }
    }

    private fun MovieDetailsViewState.State.Success<MovieDetails>.showSuccess() {
        super.showSuccess()
        this.data.setupMovieDetails()
    }

    private fun MovieDetails.setupMovieDetails() = with(binding) {
        tvTitle.text = title
        tvReleaseDate.text = releaseDate
        imgPosterContainer.imgPoster.loadPoster(
            url = posterUrl,
            placeholder = com.luisfagundes.commons_ui.R.color.mine_shaft,
            error = com.luisfagundes.commons_ui.R.drawable.spooki_media_placeholder
        )
        tvTmdbScore.text = voteAverage.formatRating(requireContext())
        tvOverview.text = overview
        imgBackdrop.loadBackdrop(backDropUrl)
        castAdapter.updateList(cast)
        //trailerAdapter.updateList(trailers)
    }
}