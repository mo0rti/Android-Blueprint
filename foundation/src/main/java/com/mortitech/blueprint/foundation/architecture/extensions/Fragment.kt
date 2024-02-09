package com.mortitech.blueprint.foundation.architecture.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mortitech.blueprint.foundation.navigation.Coordinator
import com.mortitech.blueprint.foundation.navigation.CoordinatorHost

fun Fragment.displayToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.activityCoordinator(): Coordinator {
    return (requireActivity() as CoordinatorHost<*>).coordinator
}

fun Fragment.coordinatorHost(): CoordinatorHost<*> {
    return (hostFragment(this) as? CoordinatorHost<*>)
        ?: (this.requireActivity() as? CoordinatorHost<*>)
        ?: throw Exception("The fragment must be opened in a CoordinatorHost fragment/activity")
}

fun Fragment.hostFragment(fragment: Fragment?): Fragment? {
    if (fragment == null) return null
    if (fragment is CoordinatorHost<*>) return fragment
    return hostFragment(fragment.parentFragment)
}

