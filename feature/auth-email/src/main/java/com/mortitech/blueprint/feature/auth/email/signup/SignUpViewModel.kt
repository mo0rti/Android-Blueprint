package com.mortitech.blueprint.feature.auth.email.signup

import androidx.lifecycle.viewModelScope
import com.mortitech.blueprint.data.repository.authentication.AuthenticationRepository
import com.mortitech.blueprint.foundation.architecture.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject
constructor(
    private val authenticationRepository: AuthenticationRepository
): MviViewModel<SignUpViewState, SignUpViewAction, SignUpViewEvent>() {
    override fun processViewActions(action: SignUpViewAction) {
        when (action) {
            is SignUpViewAction.FieldChanged -> {
                when (action.fieldType) {
                    SignUpFieldType.EMAIL -> updateViewState { copy(email = action.value.toString()) }
                    SignUpFieldType.PASSWORD -> updateViewState { copy(password = action.value.toString()) }
                    SignUpFieldType.CONFIRM_PASSWORD -> updateViewState { copy(confirmPassword = action.value.toString()) }
                }
            }
            is SignUpViewAction.SubmitButtonClicked -> {
                signup()
            }
        }
    }

    override fun initViewState() = SignUpViewState()

    private fun signup() {
        viewModelScope.launch {
            try {
                updateViewState { copy(isLoading = true) }
                authenticationRepository.signup(
                    email = viewState.value.email,
                    password = viewState.value.password
                )
                updateViewEvent(SignUpViewEvent.SuccessfulSignUp)
            } catch (e: Exception) {
                updateViewEvent(SignUpViewEvent.ShowError(e.message ?: "Unknown error"))
            } finally {
                updateViewState { copy(isLoading = false) }
            }
        }
    }
}