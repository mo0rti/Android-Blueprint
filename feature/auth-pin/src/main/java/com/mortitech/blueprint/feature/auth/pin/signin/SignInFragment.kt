package com.mortitech.blueprint.feature.auth.pin.signin

import androidx.fragment.app.viewModels
import com.mortitech.blueprint.feature.auth.pin.databinding.FragmentSignInPinBinding
import com.mortitech.blueprint.feature.auth.pin.navigation.AuthenticationCoordinatorEvent
import com.mortitech.blueprint.foundation.architecture.extensions.displayToast
import com.mortitech.blueprint.foundation.architecture.mvi.MviFragment
import com.mortitech.blueprint.pin.PinPadEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment :
    MviFragment<
            FragmentSignInPinBinding,
            SignInViewState,
            SignInViewAction,
            SignInViewEvent,
            SignInViewModel,>(
        FragmentSignInPinBinding::inflate,
    ), PinPadEventListener {
    override val viewModel: SignInViewModel by viewModels()

    override fun setupUI(): Unit = with(binding) {
        eventListener = this@SignInFragment
    }

    override fun onViewStateChanged(viewState: SignInViewState) {
        binding.tvPinCode.text = viewState.pin.replace("\\d".toRegex(), " * ")
    }

    override fun onViewEventReceived(viewEvent: SignInViewEvent) {
        when (viewEvent) {
            is SignInViewEvent.ShowError -> {
                displayToast(viewEvent.message)
            }
            is SignInViewEvent.SuccessfulAuthentication -> {
                requestCoordinatorEvent(AuthenticationCoordinatorEvent.StartAccountFlow)
            }
        }
    }

    override fun onChange(pinCode: String) {
        postAction(SignInViewAction.PinChanged(pinCode))
    }

    override fun onFinish(pinCode: String) {
        postAction(SignInViewAction.PinEntryFinished(pinCode))
    }
}
