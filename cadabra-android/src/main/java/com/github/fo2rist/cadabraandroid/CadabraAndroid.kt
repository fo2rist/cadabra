package com.github.fo2rist.cadabraandroid

import android.content.Context
import com.github.fo2rist.cadabra.Cadabra
import com.github.fo2rist.cadabra.CadabraConfig
import com.github.fo2rist.cadabra.Variant
import com.github.fo2rist.cadabraandroid.CadabraAndroid.Companion.initialize
import kotlin.reflect.KClass

/**
 * Android-specific cadabra extension that supports resources injection.
 * Usage:
 * - register experiment with [Cadabra.config]
 * - initialize context with [initialize]
 * - access resources associated with experiment via [getExperimentContext], see [VariantResources] for details
 */
interface CadabraAndroid : Cadabra {

    /**
     * Get Android resources accessor for active experiment variant.
     * @throws IllegalStateException if was not initialized via [initialize].
     */
    fun getExperimentContext(variantClass: KClass<out Variant>): VariantResources

    /**
     * Get Android resources accessor for active experiment variant.
     * @throws NotInitializedException if was not initialized via [initialize].
     */
    fun getExperimentContext(variantClass: Class<out Variant>): VariantResources

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
