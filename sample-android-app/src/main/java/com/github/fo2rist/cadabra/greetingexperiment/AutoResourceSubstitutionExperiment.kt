package com.github.fo2rist.cadabra.greetingexperiment

import com.github.fo2rist.cadabra.Variant
import com.github.fo2rist.cadabra.VariantId

/**
 * Variants without embedded data.
 * Demonstrate resource access by automatic resource names resolution.
 */
enum class AutoResourceVariants(
    override val id: VariantId
) : Variant {
    A(
        id = "A"
    ),
    B(
        id = "B"
    )
}
