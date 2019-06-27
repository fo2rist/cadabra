package com.github.fo2rist.cadabra

/**
 * Configuration for enabled experiments.
 * A map <Experiment ID -> Active Variant> Name.
 */
class ExperimentsConfig private constructor(
    private val experimentVariants: Map<String, String>
) {
    operator fun get(experimentId: String): String? = experimentVariants[experimentId]

    val entries: Set<Map.Entry<String, String>>
        get() = experimentVariants.entries

    companion object {

        fun create(vararg config: Pair<String, String>): ExperimentsConfig {
            return ExperimentsConfig(config.toMap())
        }
    }
}
