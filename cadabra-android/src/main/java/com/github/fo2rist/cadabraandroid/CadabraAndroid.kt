package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabra.Cadabra
import com.github.fo2rist.cadabra.CadabraConfig

/**
 * Android-specific cadabra extension that supports resources injection.
 */
interface CadabraAndroid {

    companion object {

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
