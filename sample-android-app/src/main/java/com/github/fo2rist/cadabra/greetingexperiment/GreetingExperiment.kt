package com.github.fo2rist.cadabra.greetingexperiment

import android.support.design.widget.Snackbar
import android.widget.Toast
import com.github.fo2rist.cadabra.BaseExperiment
import com.github.fo2rist.cadabra.Variant
import com.github.fo2rist.cadabra.VariantId

/**
 * Sample experiment configuration.
 * One off object that only needed to keep experiment variants together.
 */
object GreetingExperiment : BaseExperiment<GreetingVariants>(GreetingVariants::class)

/**
 * Sample experiment A/B variant for [GreetingExperiment].
 */
enum class GreetingVariants(
    override val id: VariantId,
    val message: String,
    var duration: Int,
    var type: GreetWith
) : Variant {
    A(
        "A",
        message = "Hello my friend!",
        duration = Toast.LENGTH_LONG,
        type = GreetWith.TOAST
    ),
    B(
        "B",
        message = "Hello world",
        duration = Snackbar.LENGTH_LONG,
        type = GreetWith.SNACK
    ),
    ;

    enum class GreetWith {
        TOAST, SNACK
    }
}
