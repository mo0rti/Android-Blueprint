package com.mortitech.blueprint.foundation.architecture.mvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.mortitech.blueprint.foundation.architecture.ActivityLayoutInflater
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class MviActivity<
        VB : ViewBinding,
        VS : ViewState,
        VA : ViewAction,
        VE : ViewEvent,
        VM : MviViewModel<VS, VA, VE>>
constructor(
    private val activityLayoutInflater: ActivityLayoutInflater<VB>,
): AppCompatActivity(), MviView<VB, VS, VA, VE, VM> {

    private lateinit var _binding: VB
    override val binding: VB
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = activityLayoutInflater.invoke(layoutInflater)
        setContentView(_binding.root)
        setupUI()

        // Observe view state changes and view events
        viewModel.viewState.onEach { onViewStateChanged(it) }.launchIn(lifecycleScope)
        viewModel.viewEvent.onEach { onViewEventReceived(it) }.launchIn(lifecycleScope)
    }
}
