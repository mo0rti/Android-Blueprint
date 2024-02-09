package com.mortitech.blueprint.foundation.navigation

interface CoordinatorHost<C: Coordinator> {
    val coordinator: C
}
