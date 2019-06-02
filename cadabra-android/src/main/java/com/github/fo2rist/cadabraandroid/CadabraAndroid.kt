package com.github.fo2rist.cadabraandroid

import android.content.Context
import com.github.fo2rist.cadabra.Cadabra
import com.github.fo2rist.cadabra.CadabraConfig
import com.github.fo2rist.cadabra.Variant
import com.github.fo2rist.cadabraandroid.CadabraAndroid.Companion.initialize

/**
 * Android-specific cadabra extension that supports resources injection.
 * Usage:
 * - register experiment with [Cadabra.config]
 * - initialize context with [initialize]
 * - access resources associated with experiment via [getVariantResources], see [VariantResources] for details
 */
interface CadabraAndroid : Cadabra {

    /**
     * Get Android resources accessor for given variant.
     * @throws IllegalStateException if was not initialized via [initialize].
     */
    fun getVariantResources(variant: Variant): VariantResources

    companion object {

        /**
         * Initialize Cadabra for resources access.
         * Initialization is required prior resources usage but not required to register and access experiments.
         * [Context.getApplicationContext] will be used by Cadabra as the context.
         */
        fun initialize(context: Context) {
            CadabraAndroidImpl.initialize(context)
        }

        /**
         * Entry point CadabraAndroid experiment variants usage.
         */
        val instance: CadabraAndroid
            get() = CadabraAndroidImpl

        /**
         * Entry point for Cadabra configuration.
         * Same as [Cadabra.config].
         */
        val config: CadabraConfig
            get() = Cadabra.config
    }
}
