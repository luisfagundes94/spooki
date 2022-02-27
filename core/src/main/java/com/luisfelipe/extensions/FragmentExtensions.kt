package com.luisfelipe.extensions

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import org.koin.core.component.getScopeName
import timber.log.Timber
import java.lang.Exception

fun <F : Fragment> F.requireString(any: Any?) = requireContext().requireString(any)

fun <F : Fragment, L> F.observe(
    liveData: LiveData<L>,
    onChanged: L.() -> Unit
) = liveData.observe(viewLifecycleOwner, onChanged)

fun NavController.navigateWithDeepLink(
    deepLinkDestination: String
) {
    try {
        navigate(deepLinkDestination.toUri())
    } catch (exception: Exception) {
        Timber.e(exception)
    }
}

fun Fragment.getColor(id: Int) = ContextCompat.getColor(requireContext(), id)

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(requireContext(), message, duration).show()

fun Fragment.showToast(messageList: List<CharSequence>, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(requireContext(), messageList.toString(), duration).show()
