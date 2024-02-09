package com.mortitech.blueprint.feature.auth.email.signin

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
            is SignInViewAction.FieldChanged -> {
                when (action.fieldType) {
                    SignInFieldType.EMAIL -> updateViewState { copy(email = action.value.toString()) }
                    SignInFieldType.PASSWORD -> updateViewState { copy(password = action.value.toString()) }
                }
            }
            is SignInViewAction.LoginButtonClicked -> {
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
                    email = viewState.value.email,
                    password = viewState.value.password
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
