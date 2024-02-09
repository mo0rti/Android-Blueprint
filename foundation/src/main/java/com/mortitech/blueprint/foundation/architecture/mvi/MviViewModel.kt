package com.mortitech.blueprint.foundation.architecture.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<
        VS: ViewState,
        VA: ViewAction,
        VE: ViewEvent,
        >: ViewModel() {

    private val _viewState = MutableStateFlow(initViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewAction: MutableSharedFlow<VA> = MutableSharedFlow()

    private val _viewEvent: Channel<VE> = Channel()
    val viewEvent = _viewEvent.receiveAsFlow()

    private val _coordinatorEvent: Channel<VE> = Channel()
    val coordinatorEvent = _coordinatorEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            _viewAction.collect { action ->
                processViewActions(action)
            }
        }
    }

    protected fun updateViewEvent(event: VE) {
        viewModelScope.launch { _viewEvent.send(event) }
    }

    protected fun updateViewState(reduce: VS.() -> VS) {
        _viewState.value = _viewState.value.reduce()
    }

    fun updateViewAction(action: VA) {
        viewModelScope.launch { _viewAction.emit(action) }
    }

    // Handles user actions comes from the view.
    protected abstract fun processViewActions(action: VA)
    protected abstract fun initViewState(): VS
}