package com.github.fo2rist.cadabra

import kotlin.reflect.KClass

/**
 * Cadabra A/B experiments.
 *
 * To use:
 *  - create an experiment enum implementing [Variant] interface
 *  - create [Resolver] or use one of the predefined to define which variant ot use for particular user/session.
 *  - register experiments via [Cadabra.config]'s [CadabraConfig.registerExperiment]
 *  - when it's time to apply experimental parameters get the variant via [Cadabra.instance]'s [getExperimentVariant]
 */
interface Cadabra {

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

        private val _instance = CadabraImpl()
        /**
         * Entry point Cadabra experiment variants usage.
         */
        val instance: Cadabra = _instance

        /**
         * Entry point for Cadabra configuration.
         * Use it at the application startup to keep all experiments configuration in one place.
         */
        val config: CadabraConfig = _instance
    }
}

/**
 * Cadabra's configuration.
 */
interface CadabraConfig {

    /**
     * Register experiment.
     * An experiment can only be used after it's registered.
     * Uses [variantsClass] enum name as ID.
     * @throws IllegalStateException if the experiment with the same ID is already registered.
     */
    fun <V> registerExperiment(
        variantsClass: Class<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V>

    /**
     * Register experiment.
     * An experiment can only be used after it's registered.
     * Uses [variantsClass] enum name as ID.
     * @throws IllegalStateException if the experiment with the same ID is already registered.
     */
    fun <V> registerExperiment(
        variantsClass: KClass<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V>
}
