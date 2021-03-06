package com.luisfagundes.movies.utils

import android.content.Context
import com.luisfagundes.movies.R
import com.luisfagundes.movies.utils.extensions.formatRating
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class FloatExtensionsTest {

    private val context: Context = mockk()
    private val notAvailableString = "N/A"

    @Test
    fun `WHEN float value is zero THAN format it to not available string`() {
        every { context.getString(R.string.not_available) } returns notAvailableString

        val floatNumber = 0.0f

        val result = floatNumber.formatRating(context)
        assert(result == notAvailableString)
    }

    @Test
    fun `WHEN float value is not zero THAN format it to return a string type`() {
        every { context.getString(R.string.not_available) } returns notAvailableString

        val floatNumber = 5.6f

        val result = floatNumber.formatRating(context)
        assert(result == floatNumber.toString())
    }
}