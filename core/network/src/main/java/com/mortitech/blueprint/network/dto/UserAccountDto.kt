package com.mortitech.blueprint.network.dto

import com.mortitech.blueprint.model.UserAccount

data class UserAccountDto(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val profileImageLink: String
) {
    // Convert the DTO to the domain model
    fun toUserAccount(): UserAccount {
        return UserAccount(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            profileImageLink = profileImageLink
        )
    }

    // Convert the domain model to the DTO
    constructor(userAccount: UserAccount) : this(
        id = userAccount.id,
        email = userAccount.email,
        firstName = userAccount.firstName,
        lastName = userAccount.lastName,
        profileImageLink = userAccount.profileImageLink
    )
}
