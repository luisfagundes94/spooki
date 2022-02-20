package com.luisfelipe.extensions

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation.findNavController

fun <F : Fragment> F.requireString(any: Any?) = requireContext().requireString(any)

fun <F : Fragment, L> F.observe(
    liveData: LiveData<L>,
    onChanged: L.() -> Unit
) = liveData.observe(viewLifecycleOwner, onChanged)

fun Fragment.navigateWithDeepLink(deepLinkDestination: String) {
    val request = NavDeepLinkRequest.Builder
        .fromUri(deepLinkDestination.toUri())
        .build()

    findNavController(requireView()).navigate(request)
}

fun Fragment.getColor(id: Int) = ContextCompat.getColor(requireContext(), id)

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(requireContext(), message, duration).show()

fun Fragment.showToast(messageList: List<CharSequence>, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(requireContext(), messageList.toString(), duration).show()

fun Fragment.emptyString() = ""
