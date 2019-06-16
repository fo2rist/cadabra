package com.github.fo2rist.cadabra

/**
 * A single option of the particular experiment.
 * Implement this interface in the enum that defines [Experiment] options.
 * And provide experimental parameters via properties/functions of that enum.
 * Hint: if experiment controls multiple parameter declare them as a single data-class or factory to simplify Variants
 * enum class.
 */
interface Variant {
    val id: String
}
