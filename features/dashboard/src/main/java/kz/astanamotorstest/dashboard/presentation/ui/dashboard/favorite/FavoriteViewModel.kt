package kz.astanamotorstest.dashboard.presentation.ui.dashboard.favorite

import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import kz.astanamotorstest.dashboard.domain.usecase.favorite.GetFavoriteUseCase
import kz.astanamotorstest.dashboard.entity.movie.MovieUi
import kz.astanamotorstest.dashboard.presentation.ui.dashboard.home.MovieItem
import kz.astanamotorstest.movieitem.entity.args.ItemArgs
import kz.astanamotorstest.network_components.Failure
import kz.astanamotorstest.ui_components.base.BaseViewModel
import kz.astanamotorstest.ui_components.base.Event
import kz.astanamotorstest.ui_components.extensions.setError
import kz.astanamotorstest.ui_components.extensions.setLoading
import kz.astanamotorstest.ui_components.extensions.setSuccess
import timber.log.Timber

class FavoriteViewModel(
    private val getFavoriteUseCase: GetFavoriteUseCase
) : BaseViewModel<List<MovieUi>>() {

    private val _itemArgs: LiveEvent<Event<ItemArgs>> = LiveEvent()
    val itemArgs
        get() = _itemArgs

    override fun loadData() {
        Timber.i("loadData")
        _pageResponse.setLoading()
        getFavoriteUseCase(viewModelScope, false) {
            it.either(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleSuccess(response: List<MovieUi>) {
        Timber.i("handleSuccess, response = $response")
        _pageResponse.setSuccess(response)
    }

    private fun handleFailure(f: Failure) {
        Timber.i("handleFailure, f = $f")
        _pageResponse.setError(f.exception)
    }

    fun onClickMovie(movieItem: MovieItem) {
        Timber.i("onClickMovie, movieItem = $movieItem")
        val itemArgs = ItemArgs(movieId = movieItem.movieUi.id)
        _itemArgs.postValue(Event(itemArgs))
    }
}