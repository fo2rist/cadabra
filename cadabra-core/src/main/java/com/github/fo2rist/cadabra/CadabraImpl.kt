package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.exceptions.ExperimentAlreadyRegistered
import com.github.fo2rist.cadabra.exceptions.ExperimentNotFound
import kotlin.reflect.KClass

/**
 * Single internal entry point for experiments registration and access.
 */
internal class CadabraImpl : Cadabra, CadabraConfig {

    private val resolversMap: MutableMap<String, Pair<Class<*>, Resolver<*>>> = mutableMapOf()

    override fun <V : Variant> getExperimentVariant(variantClass: Class<V>): V {
        return getExperimentVariantById(variantClass.simpleName)
    }

    override fun <V : Variant> getExperimentVariant(variantClass: KClass<V>): V {
        return getExperimentVariant(variantClass.java)
    }

    private fun <V : Variant> getExperimentVariantById(experimentId: String): V {
        val experimentResolverPair = resolversMap[experimentId]
            ?: throw ExperimentNotFound("Experiment with ID '$experimentId' is not registered")

        return experimentResolverPair.second.variant as V
    }

    override fun <V> registerExperiment(
        variantsClass: Class<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        val id = variantsClass.experimentId
        if (id in resolversMap) {
            throw ExperimentAlreadyRegistered("Experiment already registered: $id")
        }

        resolversMap[id] = Pair(variantsClass, resolver)
        return this
    }

    override fun <V> registerExperiment(
        variantsClass: KClass<V>,
        resolver: Resolver<V>
    ): CadabraConfig where V : Variant, V : Enum<V> {
        return registerExperiment(variantsClass.java, resolver)
    }
}
