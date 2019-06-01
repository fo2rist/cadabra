package com.github.fo2rist.cadabra

import kotlin.reflect.KClass

typealias ExperimentId = String

/**
 * A/B experiment with unique id and variants.
 * @param V enum that defines all available variants for the experiment.
 */
interface Experiment<V>
        where V : Variant, V : Enum<V>
{
    /**
     * Unique ID of the experiment.
     * Should never repeat for different experiments.
     * Cadabra distinguish experiments by ID only,
     * it can be also used by custom [Resolver]s to build experiment data from serialized data such as JSON.
     * By default set to class simple name, override this method if need to create more than one experiment
     * of the same class or if custom name is required for de-serialization of experiment parameters.
     */
    val id: ExperimentId
        get() = this.javaClass.simpleName

    /**
     * List of all variants available for this experiment.
     * Should be implemented as`YourVariantsEnum.values()`.
     * Providing limited subset of values will require custom [Resolver] that should never
     * return items outside of this subset.
     */
    val variants: List<V>
}

/**
 * Base A/B experiment.
 * Minimalistic implementation of [Experiment] that takes variants as the constructor parameter and
 * provides all constants from it via [variants].
 * @param V enum that defines all available variants for the experiment.
 */
abstract class BaseExperiment<V>(
    variantsClass: Class<V>
) : Experiment<V> where V : Variant, V : Enum<V> {

    constructor(variantsClass: KClass<V>) : this(variantsClass.java)

    /**
     * All variants from [V].
     */
    override val variants = variantsClass.enumConstants.toList()
}
