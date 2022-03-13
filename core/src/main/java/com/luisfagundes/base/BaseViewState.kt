package com.luisfagundes.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

abstract class BaseViewState {
    val state: MutableLiveData<State> = MutableLiveData()

    val isLoading = Transformations.map(state) { state -> state == State.LOADING }
    val isSuccess = Transformations.map(state) { state -> state == State.SUCCESS }
    val isError = Transformations.map(state) { state -> state == State.ERROR }

    enum class State {
        LOADING, SUCCESS, ERROR
    }
}