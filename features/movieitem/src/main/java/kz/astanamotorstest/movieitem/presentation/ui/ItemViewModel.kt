package kz.astanamotorstest.movieitem.presentation.ui

import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom
import kz.astanamotorstest.movieitem.domain.usecase.AddFavoriteUseCase
import kz.astanamotorstest.movieitem.domain.usecase.DeleteFavoriteUseCase
import kz.astanamotorstest.movieitem.domain.usecase.GetMovieItemUseCase
import kz.astanamotorstest.movieitem.entity.args.ItemArgs
import kz.astanamotorstest.movieitem.entity.movieitem.MovieUi
import kz.astanamotorstest.network_components.Failure
import kz.astanamotorstest.network_components.ResultState
import kz.astanamotorstest.ui_components.base.BaseViewModel
import kz.astanamotorstest.ui_components.base.Event
import kz.astanamotorstest.ui_components.extensions.setError
import kz.astanamotorstest.ui_components.extensions.setLoading
import kz.astanamotorstest.ui_components.extensions.setSuccess
import timber.log.Timber

class ItemViewModel(
    private val itemArgs: ItemArgs,
    private val getMovieItemUseCase: GetMovieItemUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel<MovieUi>() {

    private val _action: LiveEvent<Event<Actions>> = LiveEvent()
    val action
        get() = _action

    val params = GetMovieItemUseCase.Params(movieId = itemArgs.movieId)

    override fun loadData() {
        Timber.i("loadData")
        _pageResponse.setLoading()
        getMovieItemUseCase(viewModelScope, params) {
            it.either(
                ::handleFailure,
                ::handleSuccees
            )
        }
    }

    fun clickFavorite() {
        Timber.i("clickFavorite")
        if (pageResponse.value is ResultState.Success) {
            val movieUi = (pageResponse.value as ResultState.Success<MovieUi>).data
            if (movieUi.isFavorite) {
                deleteFavoriteUseCase(viewModelScope, movieUi.id) {
                    it.either(
                        ::handleFailureDelete,
                        ::handleSuccessDelete
                    )
                }
            } else {
                addFavoriteUseCase(viewModelScope, transfromMovieToRoom(movieUi)) {
                    it.either(::handleFailureAdd, ::handleSuccessAdd)
                }
            }
        }
    }

    private fun handleFailureAdd(f: Failure) {
        Timber.i("handleFailureAdd, f = $f")
        _action.postValue(Event(Actions.ON_ADD_FAVORITE_ERROR))
    }

    private fun handleSuccessAdd(response: Unit) {
        Timber.i("handleSuccessAdd, response = $response")
        val movieUi = (pageResponse.value as ResultState.Success<MovieUi>).data.apply {
            isFavorite = true
        }
        _action.postValue(Event(Actions.ON_ADD_FAVORITE_SUCCESS))
    }

    private fun handleSuccessDelete(response: Unit) {
        Timber.i("handleSuccessDelete")
        val movieUi = (pageResponse.value as ResultState.Success<MovieUi>).data.apply {
            isFavorite = false
        }
        _action.postValue(Event(Actions.ON_DELETE_FAVORITE_SUCCESS))
    }

    private fun handleFailureDelete(f: Failure) {
        Timber.i("handleFailureDelete, f = $f")
        _action.postValue(Event(Actions.ON_DELETE_FAVORITE_ERROR))
    }


    private fun handleSuccees(response: MovieUi) {
        Timber.i("handleSuccees, response = $response")
        _pageResponse.setSuccess(response)
    }

    private fun handleFailure(f: Failure) {
        Timber.i("handleFailure, f = $f")
        _pageResponse.setError(
            e = f.exception,
            message = f.errorDto?.status_message,
            code = f.errorDto?.status_code
        )
    }

    private fun transfromMovieToRoom(input: MovieUi): FavoriteRoom =
        FavoriteRoom(
            id = input.id,
            adult = input.adult,
            backdrop_path = input.backdrop_path,
            budget = input.budget,
            homepage = input.homepage ?: "",
            imdb_id = input.imdb_id,
            original_language = input.original_language,
            original_title = input.original_title,
            overview = input.overview,
            popularity = input.popularity,
            poster_path = input.poster_path,
            release_date = input.release_date,
            revenue = input.revenue,
            runtime = input.runtime,
            status = input.status,
            tagline = input.tagline,
            title = input.title,
            video = input.video,
            vote_average = input.vote_average,
            vote_count = input.vote_count
        )

    enum class Actions {
        ON_DELETE_FAVORITE_SUCCESS,
        ON_DELETE_FAVORITE_ERROR,
        ON_ADD_FAVORITE_SUCCESS,
        ON_ADD_FAVORITE_ERROR
    }
}