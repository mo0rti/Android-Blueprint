package com.mortitech.blueprint.foundation.architecture.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.mortitech.blueprint.foundation.architecture.FragmentLayoutInflater
import com.mortitech.blueprint.foundation.architecture.extensions.activityCoordinator
import com.mortitech.blueprint.foundation.architecture.extensions.coordinatorHost
import com.mortitech.blueprint.foundation.navigation.CoordinatorEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class MviFragment<
        VB : ViewBinding,
        VS : ViewState,
        VA : ViewAction,
        VE : ViewEvent,
        VM : MviViewModel<VS, VA, VE>>
constructor(
    private val layoutInflater: FragmentLayoutInflater<VB>,
) : Fragment(), MviView<VB, VS, VA, VE, VM> {

    private lateinit var _binding: VB
    override val binding: VB
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = layoutInflater.invoke(inflater, container, false)
        setupUI()

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.onEach { onViewStateChanged(it) }.launchIn(lifecycleScope)
        viewModel.viewEvent.onEach { onViewEventReceived(it) }.launchIn(lifecycleScope)
    }

    protected fun requestCoordinatorEvent(coordinatorEvent: CoordinatorEvent) {
        if (!coordinatorHost().coordinator.onEvent(coordinatorEvent)) {
            activityCoordinator().onEvent(coordinatorEvent)
        }
    }
}
