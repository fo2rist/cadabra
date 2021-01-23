package com.fo2rist.cadabra.android

import android.content.Context
import com.fo2rist.cadabra.Cadabra
import com.fo2rist.cadabra.CadabraConfig
import com.fo2rist.cadabra.Variant
import com.fo2rist.cadabra.android.CadabraAndroid.Companion.initialize
import com.fo2rist.cadabra.android.exceptions.NotInitializedException
import com.fo2rist.cadabra.exceptions.ExperimentNotFound
import kotlin.reflect.KClass

/**
 * Android-specific cadabra extension that supports resources injection.
 * Usage:
 * - register/start experiments with [Cadabra.config]
 * - initialize context with [initialize]
 * - access resources associated with experiment via [getExperimentContext], see [VariantResources] for details
 * @see [Cadabra] for experiment registration details
 */
interface CadabraAndroid : Cadabra {

    /**
     * Get Android resources accessor for active experiment variant.
     * If the experiment is not started the context would behave as normal Android context.
     * @throws ExperimentNotFound if experiment is not registered
     * @throws IllegalStateException if Cadabra was not initialized via [initialize].
     */
    fun getExperimentContext(variantClass: KClass<out Variant>): ExperimentContext

    /**
     * Get Android resources accessor for active experiment variant.
     * If the experiment is not started the context would behave as normal Android context.
     * @throws ExperimentNotFound if experiment is not registered
     * @throws NotInitializedException if Cadabra was not initialized via [initialize].
     */
    fun getExperimentContext(variantClass: Class<out Variant>): ExperimentContext

    companion object {

        private val _instance = CadabraAndroidImpl()

        /**
         * Entry point CadabraAndroid experiment variants usage.
         */
        val instance: CadabraAndroid
            get() = _instance

        /**
         * Entry point for Cadabra configuration.
         * Same as [Cadabra.config].
         */
        val config: CadabraConfig
            get() = _instance

        /**
         * Initialize Cadabra for resources access.
         * Initialization is required prior resources usage but not required to register and access experiments.
         * [Context.getApplicationContext] will be used by Cadabra as the context.
         */
        fun initialize(context: Context) {
            _instance.initialize(context)
        }
    }
}
