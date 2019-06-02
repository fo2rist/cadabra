package com.github.fo2rist.cadabra.greetingexperiment

import android.support.annotation.StringRes
import com.github.fo2rist.cadabra.*

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
    var type: GreetWith
) : Variant {
    A(
        "A",
        message = R.string.greeting_title_a,
        type = GreetWith.TOAST
    ),
    B(
        "B",
        message = R.string.greeting_title_b,
        type = GreetWith.SNACK
    ),
    ;
}
