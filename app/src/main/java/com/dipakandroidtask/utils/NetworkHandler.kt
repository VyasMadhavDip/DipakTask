package com.dipakandroidtask.utils

import android.content.Context
import android.net.NetworkCapabilities
import android.os.Build
import com.dipakandroidtask.extention.connectivityManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Dipak Vyas on 31-01-2021.
 */
@Suppress("DEPRECATION")
@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) : NetworkConnectivity {
    override fun isConnected(): Boolean {
        val connectivityManager = context.connectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network= connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }
}

interface NetworkConnectivity {
    fun isConnected(): Boolean
}