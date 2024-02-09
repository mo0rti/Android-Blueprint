package com.mortitech.blueprint.foundation.architecture.mvi

import androidx.viewbinding.ViewBinding

interface MviView<
        VB : ViewBinding,
        VS : ViewState,
        VA : ViewAction,
        VE : ViewEvent,
        VM : MviViewModel<VS, VA, VE>>
{
    val viewModel: VM
    val binding: VB

    // Setup UI related events such as onclick, onchange, list adapter initialization
    fun setupUI()
    fun onViewEventReceived(viewEvent: VE)
    fun onViewStateChanged(viewState: VS)

    fun postAction(action: VA) {
        viewModel.updateViewAction(action)
    }
}
