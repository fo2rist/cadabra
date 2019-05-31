package com.github.fo2rist.cadabra

/**
 * Cadabra A/B experiments entry point.
 * To use:
 *  - create and [Experiment] with [Variant]s for this experiments
 *  - create [Resolver] or use one of the predefined to define which variant ot use for particular user/session.
 *  - register experiments via [Cadabra.Config]
 *  - when it's time to apply experimental parameters get the variant via [getExperimentVariant]
 */
interface Cadabra {

    /**
     * Get experiment variant to apply for this user/session.
     * Will cal [Resolver.variant] every time, so make sure your resolver provides stable results if needed.
     */
    fun <E : Experiment<V>, V : Variant> getExperimentVariant(experiment: E): V

    companion object {
        val instance: Cadabra
            get() = CadabraImpl
    }

    /**
     * Entry point for Cadabra configuration.
     * Use it at the application startup
     */
    object Config {
        fun <E, V> registerExperiment(
            experiment: E,
            resolver: Resolver<V>
        ): Config where E : Experiment<V>, V : Variant, V : Enum<V> {
            CadabraImpl.registerExperiment(experiment, resolver)
            return this
        }
    }
}

internal object CadabraImpl : Cadabra {

    private val resolversMap: MutableMap<String, Pair<Experiment<*>, Resolver<*>>> = mutableMapOf()

    override fun <E : Experiment<V>, V : Variant> getExperimentVariant(experiment: E): V {
        return resolversMap[experiment.id]?.second?.variant as? V
            ?: throw IllegalStateException()
    }

    internal fun <E, V> registerExperiment(
        experiment: E,
        resolver: Resolver<V>
    ) where E : Experiment<V>, V : Variant, V : Enum<V> {
        check(experiment.id !in resolversMap) { "Experiment already registered: $experiment" }

        resolversMap[experiment.id] = Pair(experiment, resolver)
    }

    /**
     * Unregister all experiments.
     */
    internal fun reset(){
        resolversMap.clear()
    }
}
