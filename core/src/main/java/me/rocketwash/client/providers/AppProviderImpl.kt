package me.rocketwash.client.providers

import android.content.Context
import me.rocketwash.client.utils.isOnline

class AppProviderImpl(private val context: Context) : AppProvider {

    override fun getString(stringRes: Int): String {
        return context.getString(stringRes)
    }

    override fun isConnectionInternet(): Boolean {
        return context.isOnline()
    }

}