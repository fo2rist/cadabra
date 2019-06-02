package com.github.fo2rist.cadabra

import kotlin.reflect.KClass

/**
 * Cadabra A/B experiments.
 *
 * To use:
 *  - create and [Experiment] with [Variant]s for this experiments
 *  - create [Resolver] or use one of the predefined to define which variant ot use for particular user/session.
 *  - register experiments via [Cadabra.config]'s [CadabraConfig.registerExperiment]
 *  - when it's time to apply experimental parameters get the variant via [Cadabra.instance]'s [getExperimentVariant]
 */
interface Cadabra {

    /**
     * Get experiment variant to apply for this user/session.
     * Will cal [Resolver.variant] every time, so make sure your resolver provides stable results if needed.
     */
    fun <E : Experiment<V>, V : Variant> getExperimentVariant(experiment: E): V

    /**
     * Get experiment variant to apply for this user/session by [Variant] class.
     * Only works if the experiment is registered anonymously via [Variant].
     */
    fun <V : Variant> getExperimentVariant(variantClass: Class<V>): V

    /**
     * Get experiment variant to apply for this user/session by [Variant] class.
     * Only works if the experiment is registered anonymously via [Variant].
     */
    fun <V : Variant> getExperimentVariant(variantClass: KClass<V>): V

    companion object {
        /**
         * Entry point Cadabra experiment variants usage.
         */
        val instance: Cadabra
            get() = CadabraImpl

        /**
         * Entry point for Cadabra configuration.
         * Use it at the application startup to keep all experiments configuration in one place.
         */
        val config: CadabraConfig
            get() = CadabraImpl
    }

}

/**
 * Cadabra's configuration.
 */
interface CadabraConfig {

    /**
     * Register experiment.
     * An experiment can only be used after it's registered.
     * @throws IllegalStateException if the experiment with the same ID is already registered.
     */
    fun <E, V> registerExperiment(
        experiment: E,
        resolver: Resolver<V>
    ): CadabraConfig where E : Experiment<V>, V : Variant, V : Enum<V>

    /**
     * Register "anonymous" experiment for given variants.
     * Creates a default [Experiment] for given variants using [variantsClass] enum name as ID.
     * @throws IllegalStateException if the experiment with the same ID is already registered.
     */
    fun <V> registerExperiment(
        variantsClass: Class<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V>

    /**
     * Register "anonymous" experiment for given variants.
     * Creates a default [Experiment] for given variants using [variantsClass] enum name as ID.
     * @throws IllegalStateException if the experiment with the same ID is already registered.
     */
    fun <V> registerExperiment(
        variantsClass: KClass<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V>
}
