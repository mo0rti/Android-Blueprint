package com.mortitech.blueprint.feature.auth.email.signup

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.mortitech.blueprint.feature.auth.email.databinding.FragmentSignUpBinding
import com.mortitech.blueprint.foundation.architecture.extensions.displayToast
import com.mortitech.blueprint.foundation.architecture.extensions.gone
import com.mortitech.blueprint.foundation.architecture.extensions.visible
import com.mortitech.blueprint.foundation.architecture.mvi.MviFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment :
    MviFragment<
            FragmentSignUpBinding,
            SignUpViewState,
            SignUpViewAction,
            SignUpViewEvent,
            SignUpViewModel,>(
        FragmentSignUpBinding::inflate,
    ) {
    override val viewModel: SignUpViewModel by viewModels()

    override fun setupUI(): Unit = with(binding) {
        btnSignUp.setOnClickListener {
        }
        etEmail.addTextChangedListener {
            postAction(SignUpViewAction.FieldChanged(SignUpFieldType.EMAIL, it.toString()))
        }
        etPassword.addTextChangedListener {
            postAction(SignUpViewAction.FieldChanged(SignUpFieldType.PASSWORD, it.toString()))
        }
        etConfirmPassword.addTextChangedListener {
            postAction(
                SignUpViewAction.FieldChanged(
                    SignUpFieldType.CONFIRM_PASSWORD,
                    it.toString()
                )
            )
        }
    }

    override fun onViewStateChanged(viewState: SignUpViewState): Unit = with(binding) {
        if (viewState.isLoading) {
            btnSignUp.gone()
            pbLoading.visible()
        } else {
            pbLoading.gone()
            btnSignUp.visible()
        }
    }

    override fun onViewEventReceived(viewEvent: SignUpViewEvent) {
        when (viewEvent) {
            is SignUpViewEvent.ShowError -> {
                displayToast(viewEvent.message)
            }
            is SignUpViewEvent.SuccessfulSignUp -> {
                displayToast("Success")
            }
        }
    }
}
