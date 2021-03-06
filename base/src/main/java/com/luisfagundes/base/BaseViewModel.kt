package com.luisfagundes.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<ViewState, ViewAction> : ViewModel() {
    abstract val viewState: ViewState
    abstract fun dispatchViewAction(viewAction: ViewAction)

    protected fun ViewModel.executeCoroutines(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        action: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(dispatcher, block = action)
}
