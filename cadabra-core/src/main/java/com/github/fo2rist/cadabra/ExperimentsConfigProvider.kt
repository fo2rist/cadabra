package com.github.fo2rist.cadabra

/**
 * Provider of [ExperimentsConfig] that may configure experiments asynchronously.
 * Can be used if the experiment config not know in advance or takes a lot of time to load.
 * Extend this class and provide instance to [CadabraConfig.activateExperimentsAsync].
 * Once the config is ready call [provideConfig].
 */
abstract class ExperimentsConfigProvider {

    // Function that accepts config to activate it.
    private var activateConfigFun: ((ExperimentsConfig) -> Unit)? = null

    /**
     * Tell Cadabra that updated config is available.
     * Can be called multiple times.
     */
    fun provideConfig(config: ExperimentsConfig) {
        activateConfigFun?.invoke(config)
    }

    /**
     * Override this function to get notified when provider is attached to Cadabra.
     * By default does nothing.
     * Note that Cadabra may call this method synchronously when [CadabraConfig.activateExperimentsAsync] is called.
     */
    open fun onAttached() = Unit

    /**
     * Register provider at Cadabra.
     * @param activateConfig function that config provider will call once config is ready.
     */
    internal fun attach(activateConfig: (ExperimentsConfig) -> Unit) {
        this.activateConfigFun = activateConfig
        this.onAttached()
    }
}
