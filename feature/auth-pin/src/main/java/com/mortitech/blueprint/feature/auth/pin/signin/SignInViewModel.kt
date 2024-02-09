package com.mortitech.blueprint.feature.auth.pin.signin

import androidx.lifecycle.viewModelScope
import com.mortitech.blueprint.data.repository.authentication.AuthenticationRepository
import com.mortitech.blueprint.foundation.architecture.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject
constructor(
    private val authenticationRepository: AuthenticationRepository
): MviViewModel<SignInViewState, SignInViewAction, SignInViewEvent>() {
    override fun processViewActions(action: SignInViewAction) {
        when (action) {
            is SignInViewAction.PinChanged -> {
                updateViewState { copy(pin = action.pin) }
            }
            is SignInViewAction.PinEntryFinished -> {
                signin()
            }
        }
    }

    override fun initViewState() = SignInViewState()

    private fun signin() {
        viewModelScope.launch {
            try {
                updateViewState { copy(isLoading = true) }
                authenticationRepository.signin(
                    pincode = viewState.value.pin,
                )
                updateViewEvent(SignInViewEvent.SuccessfulAuthentication)
            } catch (e: Exception) {
                updateViewEvent(SignInViewEvent.ShowError(e.message ?: "Unknown error"))
            } finally {
                updateViewState { copy(isLoading = false) }
            }
        }
    }
}
