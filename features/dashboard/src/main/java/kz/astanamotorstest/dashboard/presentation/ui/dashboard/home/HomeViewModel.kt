package kz.astanamotorstest.dashboard.presentation.ui.dashboard.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import kz.astanamotorstest.dashboard.domain.usecase.movie.GetPopularityMoviesUseCase
import kz.astanamotorstest.dashboard.entity.movie.MovieResponseUi
import kz.astanamotorstest.dashboard.entity.movie.MovieUi
import kz.astanamotorstest.movieitem.entity.args.ItemArgs
import kz.astanamotorstest.network_components.Failure
import kz.astanamotorstest.network_components.ResultState
import kz.astanamotorstest.ui_components.base.BaseViewModel
import kz.astanamotorstest.ui_components.base.Event
import kz.astanamotorstest.ui_components.extensions.setError
import kz.astanamotorstest.ui_components.extensions.setLoading
import kz.astanamotorstest.ui_components.extensions.setSuccess
import timber.log.Timber

class HomeViewModel(
    private val getPopularityMoviesUseCase: GetPopularityMoviesUseCase
) : BaseViewModel<List<MovieUi>>() {

    private val params = GetPopularityMoviesUseCase.Params(page = 1)
    private val items = ArrayList<MovieUi>()

    private val _itemArgs: LiveEvent<Event<ItemArgs>> = LiveEvent()
    val itemArgs
        get() = _itemArgs

    override val loading: LiveData<Boolean> = Transformations.map(_pageResponse) {
        it is ResultState.Loading && params.page == 1
    }

    override val errorResponse: LiveData<Boolean> = Transformations.map(_pageResponse) {
        it is ResultState.Error && params.page == 1
    }

    override fun loadData() {
        Timber.i("loadData")
        _pageResponse.setLoading()
        getPopularityMoviesUseCase(viewModelScope, params) {
            it.either(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    fun loadNewData() {
        Timber.i("loadNewData")
        clearList()
        loadData()
    }

    fun clearList(){
        Timber.i("clearList")
        params.page = 1
        items.clear()
    }
    private fun handleSuccess(response: MovieResponseUi) {
        Timber.i("handleSuccess, response = $response")
        items.addAll(response.results)
        _pageResponse.setSuccess(items)
        params.page++
    }

    fun onClickMovie(movieItem: MovieItem) {
        Timber.i("onClickMovie, movieItem = $movieItem")
        val itemArgs = ItemArgs(movieId = movieItem.movieUi.id)
        _itemArgs.postValue(Event(itemArgs))
    }

    private fun handleFailure(f: Failure) {
        Timber.i("handleFailure, f = $f")
        _pageResponse.setError(
            e = f.exception,
            message = f.errorDto?.status_message,
            code = f.errorDto?.status_code
        )
    }
}