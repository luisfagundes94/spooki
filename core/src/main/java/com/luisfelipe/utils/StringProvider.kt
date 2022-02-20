package com.luisfelipe.utils

import android.content.Context

interface StringProvider {
    fun getString(id: Int, vararg args: Any): String
    fun getQuantityString(id: Int, quantity: Int, vararg formatArgs: Any): String
}

class StringProviderImpl(
    private val context: Context
) : StringProvider {

    override fun getString(id: Int, vararg args: Any): String {
        return context.getString(id, *args)
    }

    override fun getQuantityString(id: Int, quantity: Int, vararg formatArgs: Any): String {
        return context.resources.getQuantityString(id, quantity, *formatArgs)
    }
}