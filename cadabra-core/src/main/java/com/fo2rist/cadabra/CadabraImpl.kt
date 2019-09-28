package com.fo2rist.cadabra

import com.fo2rist.cadabra.exceptions.ExperimentNotFound
import com.fo2rist.cadabra.exceptions.UnknownVariant
import com.fo2rist.cadabra.exceptions.VariantNotFound
import com.fo2rist.cadabra.resolvers.StaticResolver
import kotlin.reflect.KClass


/**
 * Single internal entry point for experiments registration and access.
 */
internal class CadabraImpl : Cadabra, CadabraConfig {

    private val resolversMap: MutableMap<String, Pair<Class<out Variant>, Resolver<*>?>> = mutableMapOf()

    override fun <V : Variant> getExperimentVariant(experiment: KClass<V>): V? {
        return getExperimentVariant(experiment.java)
    }

    override fun <V : Variant> getExperimentVariant(experiment: Class<V>): V? {
        val experimentResolverPair = resolversMap[experiment.experimentId]
            ?: throw ExperimentNotFound("Experiment '$experiment' is not registered")

        val result = experimentResolverPair.second?.variant
            ?: return null

        return experiment.cast(result)
    }

    override fun <V> registerExperiment(
        experiment: Class<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        updateResolver(experiment, null)
        return this
    }

    override fun <V> registerExperiment(
        experiment: KClass<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        updateResolver(experiment.java, null)
        return this
    }

    override fun startExperiments(config: ExperimentsConfig) {
        for ((experimentId, variantName) in config.entries) {
            val experiment = resolversMap[experimentId]?.first
                ?: continue

            val variant = experiment.variantByName(variantName)
                ?: throw UnknownVariant("Variant with name $variantName not found in class $experiment")

            updateResolver(experiment, StaticResolver(variant))
        }
    }

    override fun startExperimentsAsync(configProvider: ExperimentsConfigProvider): CadabraConfig {
        configProvider.attach(this::startExperiments)
        return this
    }

    override fun <V> startExperiment(
        experiment: KClass<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        updateResolver(experiment.java, resolver)
        return this
    }

    override fun <V> startExperiment(
        experiment: Class<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        updateResolver(experiment, resolver)
        return this
    }

    override fun <V> startExperiment(
        experiment: KClass<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        val defaultVariant = experiment.defaultVariant
            ?: throw VariantNotFound("$experiment class doesn't have enum constants")

        updateResolver(experiment.java, StaticResolver(defaultVariant))
        return this
    }

    //null resolver effectively makes the experiment inactive (waiting for start)
    private fun updateResolver(
        experiment: Class<out Variant>,
        resolver: Resolver<out Variant>?
    ) {
        resolversMap[experiment.experimentId] = Pair(experiment, resolver)
    }
}
