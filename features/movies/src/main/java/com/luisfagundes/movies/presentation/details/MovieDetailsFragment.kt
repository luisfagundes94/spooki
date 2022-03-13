package com.luisfagundes.movies.presentation.details

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.domain.model.MovieDetails
import com.luisfagundes.extensions.loadPoster
import com.luisfagundes.extensions.observe
import com.luisfelipe.movies.R
import com.luisfelipe.movies.databinding.FragmentMovieDetailsBinding
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
        tvReleaseDate.text = movie.releaseDate
        imgPoster.imgPoster.loadPoster(movie.posterUrl)
        tvTmdbScore.text = movie.voteAverage.toString()
        tvOverview.text = movie.overview
        imgBackdrop.loadBackdrop(movie.backDropUrl)
        castAdapter.updateList(movie.cast)
    }
}