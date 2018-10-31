package me.rocketwash.client.providers

import android.support.annotation.StringRes

interface AppProvider {

    fun getString(@StringRes stringRes: Int): String

    fun isConnectionInternet(): Boolean

}