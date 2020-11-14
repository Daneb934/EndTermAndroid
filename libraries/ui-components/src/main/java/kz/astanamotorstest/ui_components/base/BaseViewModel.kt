package kz.astanamotorstest.ui_components.base

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kz.astanamotorstest.network_components.ResultState

open class BaseViewModel<T>: ViewModel() {

    var args: Bundle? = null

    protected val _pageResponse = MutableLiveData<ResultState<T>>()
    val pageResponse: LiveData<ResultState<T>> get() = _pageResponse

    open val loading = Transformations.map(pageResponse) {
        it is ResultState.Loading
    }
    val success = Transformations.map(pageResponse) {
        it is ResultState.Success
    }
    open val errorResponse = Transformations.map(pageResponse) {
        it is ResultState.Error
    }

    open fun loadData() {}

    fun setArguments(bundle: Bundle?) {
        args = bundle
    }
}