package com.github.fo2rist.cadabra

/**
 * A single option (variant) of the particular experiment.
 * Implement this interface by the enum so that each item represents the experiment variant,
 * and provide experimental parameters via properties/functions of that enum.
 *
 * Hint: if experiment controls multiple parameter declare them as a single data-class or factory to simplify variants
 * enum class.
 */
interface Variant {
    /**
     * Name of the variant.
     * Given that variants are implemented as enums it should be always provided by [Enum.name]
     * with not implementation required for Kotlin.
     * For java implement `String getName()` by delegating to [java.lang.Enum.name].
     */
    val name: String
}

/**
 * Get ID of the experiment.
 */
val <V> Class<V>.experimentId: String where V : Variant, V : Enum<V>
    get() = this.simpleName
