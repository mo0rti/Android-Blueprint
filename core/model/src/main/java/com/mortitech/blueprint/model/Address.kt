package com.mortitech.blueprint.model

import com.mortitech.blueprint.model.annotations.GenerateCode

@GenerateCode
data class Address(
    val streetName: String,
    val houseNumber: Int,
    val city: String,
    val postalCode: String,
    val country: String
)
