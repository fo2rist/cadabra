package com.github.fo2rist.cadabra.greetingexperiment

import android.support.annotation.StringRes
import com.github.fo2rist.cadabra.BaseExperiment
import com.github.fo2rist.cadabra.MainActivityParameters.MessageStyle
import com.github.fo2rist.cadabra.R
import com.github.fo2rist.cadabra.Variant
import com.github.fo2rist.cadabra.VariantId

/**
 * Sample experiment configuration.
 * Demonstrates manual experiment object creation.
 */
internal object PlainExperiment : BaseExperiment<PlainVariants>(PlainVariants::class)

/**
 * Variants for [PlainExperiment].
 * Demonstrate experiment data access via variant enum itself.
 */
internal enum class PlainVariants(
    override val id: VariantId,
    @StringRes
    val message: Int,
    var type: MessageStyle
) : Variant {
    A(
        "A",
        message = R.string.greeting_title_a,
        type = MessageStyle.TOAST
    ),
    B(
        "B",
        message = R.string.greeting_title_b,
        type = MessageStyle.SNACK
    ),
    ;
}
