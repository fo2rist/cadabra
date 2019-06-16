package com.github.fo2rist.cadabra.greetingexperiment

import com.github.fo2rist.cadabra.Variant

/**
 * Variants without embedded data.
 * Demonstrate resource access by automatic resource names resolution.
 */
enum class AutoResourceVariants(
    override val id: String
) : Variant {
    A(
        id = "A"
    ),
    B(
        id = "B"
    )
}
