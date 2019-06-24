package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.exceptions.ExperimentAlreadyRegistered
import com.github.fo2rist.cadabra.exceptions.ExperimentNotActive
import com.github.fo2rist.cadabra.exceptions.ExperimentNotFound
import com.github.fo2rist.cadabra.exceptions.UnknownVariant
import kotlin.reflect.KClass

/**
 * Cadabra A/B experiments.
 *
 * To use:
 *  - create an experiment enum implementing [Variant] interface
 *  - create [Resolver] or use one of the predefined to define which variant ot use for particular user/session.
 *  - register and start experiments via [Cadabra.config]
 *  - when it's time to apply experimental parameters get the variant via [Cadabra.instance]'s [getExperimentVariant]
 *
 *  Experiment registration:
 *  When experiment is registered via [CadabraConfig.registerExperiment] it become discoverable by name but inactive.
 *  For inactive experiment [getExperimentVariant] can not be used yet, to activate registered experiments use
 *  [CadabraConfig.activateExperiments]. You can register multiple experiments and then activate as many as needed with
 *  a single [CadabraConfig.activateExperiments] call.
 *  Or use [CadabraConfig.startExperiment] to register and activate experiment at the same time.
 */
interface Cadabra {

    /**
     * Get experiment variant to apply for this user/session by [Variant] class.
     * Only works if the experiment is registered and active.
     * @see [CadabraConfig.registerExperiment]
     * @see [CadabraConfig.startExperiment]
     * @throws ExperimentNotFound if experiment is not registered
     * @throws ExperimentNotActive is experiment was not activated
     */
    fun <V : Variant> getExperimentVariant(experiment: KClass<V>): V

    /**
     * Get experiment variant to apply for this user/session by [Variant] class.
     * Only works if the experiment is registered and active.
     * @see [CadabraConfig.registerExperiment]
     * @see [CadabraConfig.startExperiment]
     * @throws ExperimentNotFound if experiment is not registered
     * @throws ExperimentNotActive is experiment was not activated
     */
    fun <V : Variant> getExperimentVariant(experiment: Class<V>): V

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
     * Uses [experiment] enum name as ID.
     * An experiment can only be used after it's registered.
     * @throws ExperimentAlreadyRegistered is the experiment with the same ID already registered
     */
    fun <V> registerExperiment(
        experiment: KClass<V>
    ): CadabraConfig where V : Variant, V : Enum<V>

    /**
     * Register experiment.
     * Uses [experiment] enum name as ID.
     * An experiment can only be used after it's registered.
     * @throws ExperimentAlreadyRegistered is the experiment with the same ID already registered
     */
    fun <V> registerExperiment(
        experiment: Class<V>
    ): CadabraConfig where V : Variant, V : Enum<V>

    /**
     * Activate previously registered experiments.
     * Experiments previously activated will be reactivated with a new active variant specified.
     * Experiments with unknown IDs will be ignored.
     * @throws UnknownVariant if provided variant doesn't match registered experiment
     */
    fun activateExperiments(experimentsConfig: ExperimentsConfig)

    /**
     * Register & start experiment.
     * A combination of [registerExperiment] & [activateExperiments].
     * @throws ExperimentAlreadyRegistered is the experiment with the same ID already registered
     */
    fun <V> startExperiment(
        experiment: KClass<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V>

    /**
     * Register & start experiment.
     * A combination of [registerExperiment] & [activateExperiments].
     * @throws ExperimentAlreadyRegistered is the experiment with the same ID already registered
     */
    fun <V> startExperiment(
        experiment: Class<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V>
}
