package com.luisfelipe.movies.presentation.details

import androidx.navigation.fragment.navArgs
import com.luisfelipe.base.BaseFragment
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.domain.model.MovieDetails
import com.luisfelipe.extensions.observe
import com.luisfelipe.movies.R
import com.luisfelipe.movies.databinding.FragmentMovieDetailsBinding
import com.luisfelipe.movies.presentation.details.adapter.CastAdapter
import com.luisfelipe.movies.utils.loadBackdrop
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(
    successViewId = R.id.success_movie_details_container,
    loadingViewId = R.id.loading_movie_details_container,
    errorViewId = R.id.error_movie_details_container,
) {

    private val viewModel: MovieDetailsViewModel by viewModel()
    private val adapter: CastAdapter by inject()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onBind() = FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun FragmentMovieDetailsBinding.onViewCreated() {
        setupUpActionButton()
        setupObservers()

        viewModel.dispatchViewAction(MovieDetailsViewAction.FetchMovieDetails(args.movieId))
    }

    private fun setupObservers() {
        observe(viewModel.viewState.state) {
            when (this) {
                BaseViewState.State.SUCCESS -> showSuccess()
                BaseViewState.State.LOADING -> showLoading()
                BaseViewState.State.ERROR -> showError()
                else -> showError()
            }
        }

        observe(viewModel.viewState.movieDetails) {
            setupMovieDetails(this)
        }
    }

    private fun setupMovieDetails(movie: MovieDetails) = with(binding) {
        tvTitle.text = movie.title
        tvTmdbScore.text = movie.voteAverage.toString()
        tvOverview.text = movie.overview
        imgBackdrop.loadBackdrop(movie.backDropUrl)
    }
}