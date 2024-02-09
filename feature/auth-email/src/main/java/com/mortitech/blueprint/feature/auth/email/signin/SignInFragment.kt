package com.mortitech.blueprint.feature.auth.email.signin

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.mortitech.blueprint.feature.auth.email.databinding.FragmentSignInBinding
import com.mortitech.blueprint.feature.auth.email.navigation.AuthenticationCoordinatorEvent
import com.mortitech.blueprint.foundation.architecture.extensions.displayToast
import com.mortitech.blueprint.foundation.architecture.extensions.gone
import com.mortitech.blueprint.foundation.architecture.extensions.visible
import com.mortitech.blueprint.foundation.architecture.mvi.MviFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment :
    MviFragment<
            FragmentSignInBinding,
            SignInViewState,
            SignInViewAction,
            SignInViewEvent,
            SignInViewModel,>(
        FragmentSignInBinding::inflate,
    ) {
    override val viewModel: SignInViewModel by viewModels()

    override fun setupUI(): Unit = with(binding) {
        btnSignIn.setOnClickListener {
            postAction(SignInViewAction.LoginButtonClicked)
        }
        etEmail.addTextChangedListener {
            postAction(SignInViewAction.FieldChanged(SignInFieldType.EMAIL, it.toString()))
        }
        etPassword.addTextChangedListener {
            postAction(SignInViewAction.FieldChanged(SignInFieldType.PASSWORD, it.toString()))
        }
        btnSignUp.setOnClickListener {
            requestCoordinatorEvent(AuthenticationCoordinatorEvent.NavigateToSignUp)
        }
    }

    override fun onViewStateChanged(viewState: SignInViewState): Unit = with(binding) {
        if (viewState.isLoading) {
            btnSignIn.gone()
            pbLoading.visible()
        } else {
            pbLoading.gone()
            btnSignIn.visible()
        }
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
}
