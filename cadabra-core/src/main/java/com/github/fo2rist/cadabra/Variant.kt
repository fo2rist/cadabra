package com.github.fo2rist.cadabra

import kotlin.reflect.KClass

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
 * Get ID (simple class name) of the experiment.
 */
val <V> KClass<V>.experimentId: String where V : Variant
    get() = this.java.experimentId

/**
 * Get ID (simple class name) of the experiment.
 */
val <V> Class<V>.experimentId: String where V : Variant
    get() = this.simpleName

/**
 * Find enum item by its [Enum.name].
 */
internal fun <V : Variant> Class<V>.variantByName(name: String): V? {
    return this.enumConstants.find { it.name == name }
}

/**
 * Get first enum item or null if class doesn't have any.
 */
internal val <V : Variant> KClass<V>.defaultVariant: V?
    get() = this.java.enumConstants?.getOrNull(0)
