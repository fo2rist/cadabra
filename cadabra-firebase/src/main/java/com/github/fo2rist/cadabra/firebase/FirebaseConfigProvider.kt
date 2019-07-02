package com.github.fo2rist.cadabra.firebase

import android.support.annotation.VisibleForTesting
import com.github.fo2rist.cadabra.ExperimentsConfig
import com.github.fo2rist.cadabra.ExperimentsConfigProvider
import com.github.fo2rist.cadabraandroid.configFromJson
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

const val CADABRA_CONFIG_KEY = "cadabra_experiments"

/**
 * [ExperimentsConfigProvider] that starts experiments automatically by Firebase Remote Config.
 * Loads [ExperimentsConfig] as Json from specified remote config field.
 * @see com.github.fo2rist.cadabraandroid.configFromJson
 */
class FirebaseConfigProvider : ExperimentsConfigProvider {
    private val configKey: String
    private val fetchAutomatically: Boolean
    private val useDefaults: Boolean
    private val firebaseConfig: FirebaseRemoteConfig

    /**
     * Create Firebase Config Provider.
     * Be default provider automatically fetches the default config and initiates the remote config loading.
     * If the automatic fetching is turned off or the remote config was updated [startExperimentFromRemoteConfig]
     * triggers re-loading.
     * @param configKey name of key to be fetched from Firebase Remote Config, be default [CADABRA_CONFIG_KEY]
     * @param fetchAutomatically automatically fetch and activate latest Firebase Config once attached to Cadabra
     * @param useDefaults automatically load default Firebase Config values once attached to Cadabra
     */
    @JvmOverloads
    constructor(
        configKey: String = CADABRA_CONFIG_KEY,
        fetchAutomatically: Boolean = true,
        useDefaults: Boolean = true
    ) : this(configKey, fetchAutomatically, useDefaults, FirebaseRemoteConfig.getInstance())

    @VisibleForTesting
    internal constructor(
        configKey: String,
        fetchAutomatically: Boolean,
        useDefaults: Boolean,
        firebaseConfig: FirebaseRemoteConfig
    ) {
        this.configKey = configKey
        this.fetchAutomatically = fetchAutomatically
        this.useDefaults = useDefaults
        this.firebaseConfig = firebaseConfig
    }

    override fun onAttached() {
        if (this.useDefaults) {
            startExperimentFromRemoteConfig()
        }

        if (this.fetchAutomatically) {
            firebaseConfig.fetchAndActivate().addOnCompleteListener {
                startExperimentFromRemoteConfig()
            }
        }
    }

    /**
     * Apply current active remote config.
     */
    fun startExperimentFromRemoteConfig() {
        val experiments = firebaseConfig.getString(configKey)
        val config = configFromJson(experiments)
        provideConfig(config)
    }
}
