package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.exceptions.ExperimentAlreadyRegistered
import com.github.fo2rist.cadabra.exceptions.ExperimentNotActive
import com.github.fo2rist.cadabra.exceptions.ExperimentNotFound
import com.github.fo2rist.cadabra.exceptions.UnknownVariant
import com.github.fo2rist.cadabra.resolvers.StaticResolver
import kotlin.reflect.KClass


/**
 * Single internal entry point for experiments registration and access.
 */
internal class CadabraImpl : Cadabra, CadabraConfig {

    private val resolversMap: MutableMap<String, Pair<Class<out Variant>, Resolver<*>?>> = mutableMapOf()

    override fun <V : Variant> getExperimentVariant(experiment: KClass<V>): V {
        return getExperimentVariant(experiment.java)
    }

    override fun <V : Variant> getExperimentVariant(experiment: Class<V>): V {
        val experimentResolverPair = resolversMap[experiment.experimentId]
            ?: throw ExperimentNotFound("Experiment '$experiment' is not registered")

        // TODO maybe return just default option, or create registration with default option 06/22/2019
        val result = experimentResolverPair.second?.variant
            ?: throw ExperimentNotActive("Experiment '$experiment' is not active")

        return experiment.cast(result)
    }

    override fun <V> registerExperiment(
        experiment: Class<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        registerNew(experiment, null)
        return this
    }

    override fun <V> registerExperiment(
        experiment: KClass<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        registerNew(experiment.java, null)
        return this
    }

    override fun activateExperiments(config: ExperimentsConfig) {
        for ((experimentId, variantName) in config.entries) {
            val experiment = resolversMap[experimentId]?.first
                ?: continue

            val variant = experiment.variantByName(variantName)
                ?: throw UnknownVariant("Variant with name $variantName not found in class $experiment")

            updateResolver(experiment, StaticResolver(variant))
        }
    }

    override fun activateExperimentsAsync(configProvider: ExperimentsConfigProvider) {
        configProvider.attach(this::activateExperiments)
    }

    override fun <V> startExperiment(
        experiment: KClass<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        registerNew(experiment.java, resolver)
        return this
    }

    override fun <V> startExperiment(
        experiment: Class<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        registerNew(experiment, resolver)
        return this
    }

    private fun <V> registerNew(
        experiment: Class<V>,
        resolver: Resolver<V>?
    ) where V : Variant {
        val experimentId = experiment.experimentId
        if (experimentId in resolversMap) {
            throw ExperimentAlreadyRegistered("Experiment already registered: $experimentId")
        }

        updateResolver(experiment, resolver)
    }

    //null resolver effectively makes the experiment inactive
    private fun updateResolver(
        experiment: Class<out Variant>,
        resolver: Resolver<out Variant>?
    ) {
        resolversMap[experiment.experimentId] = Pair(experiment, resolver)
    }
}
