package com.github.fo2rist.cadabra

import kotlin.reflect.KClass

/**
 * Single internal entry point for experiments registration and access.
 */
internal class CadabraImpl : Cadabra, CadabraConfig {

    private val resolversMap: MutableMap<String, Pair<Experiment<*>, Resolver<*>>> = mutableMapOf()

    override fun <E : Experiment<V>, V : Variant> getExperimentVariant(experiment: E): V {
        return getExperimentVariantById(experiment.id)
    }

    override fun <V : Variant> getExperimentVariant(variantClass: Class<V>): V {
        return getExperimentVariantById(variantClass.simpleName)
    }


    override fun <V : Variant> getExperimentVariant(variantClass: KClass<V>): V {
        return getExperimentVariant(variantClass.java)
    }

    private fun <V : Variant> getExperimentVariantById(experimentId: ExperimentId): V {
        val experimentResolverPair = resolversMap[experimentId]
            ?: throw IllegalStateException("Experiment with ID '$experimentId' is not registered")

        return experimentResolverPair.second.variant as V
    }

    override fun <E, V> registerExperiment(
        experiment: E,
        resolver: Resolver<V>
    ): CadabraConfig where E : Experiment<V>, V : Variant, V : Enum<V> {
        check(experiment.id !in resolversMap) { "Experiment already registered: $experiment" }

        resolversMap[experiment.id] = Pair(experiment, resolver)
        return this
    }

    override fun <V> registerExperiment(
        variantsClass: Class<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        val experiment = object : BaseExperiment<V>(variantsClass) {
            override val id: ExperimentId = variantsClass.simpleName
        }

        return registerExperiment(experiment, resolver)
    }

    override fun <V> registerExperiment(
        variantsClass: KClass<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        return registerExperiment(variantsClass.java, resolver)
    }
}
