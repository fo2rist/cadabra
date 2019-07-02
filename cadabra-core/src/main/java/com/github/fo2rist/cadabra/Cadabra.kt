package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.exceptions.ExperimentNotFound
import com.github.fo2rist.cadabra.exceptions.ExperimentNotStarted
import com.github.fo2rist.cadabra.exceptions.UnknownVariant
import com.github.fo2rist.cadabra.exceptions.VariantNotFound
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
 *  Experiment registration & start:
 *  When experiment is registered via [CadabraConfig.registerExperiment] it become discoverable by name and waiting
 *  to be started. For such experiment [getExperimentVariant] can not be used yet.
 *  To start registered experiments use [CadabraConfig.startExperiments]. You can register multiple experiments and then
 *  start as many as needed with a single [CadabraConfig.startExperiments] call.
 *  Or use [CadabraConfig.startExperiment] to register and start experiment at the same time.
 */
interface Cadabra {

    /**
     * Get experiment variant to apply for this user/session by [Variant] class.
     * Only works if the experiment is started.
     * @see [CadabraConfig.registerExperiment]
     * @see [CadabraConfig.startExperiment]
     * @throws ExperimentNotFound if experiment is not registered
     * @throws ExperimentNotStarted is experiment was not started
     */
    fun <V : Variant> getExperimentVariant(experiment: KClass<V>): V

    /**
     * Get experiment variant to apply for this user/session by [Variant] class.
     * Only works if the experiment is started.
     * @see [CadabraConfig.registerExperiment]
     * @see [CadabraConfig.startExperiment]
     * @throws ExperimentNotFound if experiment is not registered
     * @throws ExperimentNotStarted is experiment was not started
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
     */
    fun <V> registerExperiment(
        experiment: KClass<V>
    ): CadabraConfig where V : Variant, V : Enum<V>

    /**
     * Register experiment.
     * Uses [experiment] enum name as ID.
     * An experiment can only be used after it's registered.
     */
    fun <V> registerExperiment(
        experiment: Class<V>
    ): CadabraConfig where V : Variant, V : Enum<V>

    /**
     * Start previously registered experiments.
     * Experiments previously started will be reconfigured with a new active variant specified.
     * Experiments with unknown IDs will be ignored.
     * @throws UnknownVariant if provided variant doesn't match registered experiment
     */
    fun startExperiments(config: ExperimentsConfig)

    /**
     * Start previously registered experiment asynchronously.
     * Registers the [ExperimentsConfigProvider] that can update experiments config at any time.
     * When it provides the new config via [provideConfig][ExperimentsConfigProvider.provideConfig] it works the same
     * way as [startExperiments].
     */
    fun startExperimentsAsync(configProvider: ExperimentsConfigProvider)

    /**
     * Register & start experiment.
     * A combination of [registerExperiment] & [startExperiments].
     */
    fun <V> startExperiment(
        experiment: KClass<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V>

    /**
     * Register & start experiment.
     * A combination of [registerExperiment] & [startExperiments].
     */
    fun <V> startExperiment(
        experiment: Class<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V>

    /**
     * Register & start experiment with default variant active.
     * The first item of variants enum is the default one.
     * A combination of [registerExperiment] & [startExperiments].
     * @throws VariantNotFound if provided experiment doesn't have variants
     */
    fun <V> startExperiment(
        experiment: KClass<V>
    ): CadabraConfig where V : Variant, V : Enum<V>
}
