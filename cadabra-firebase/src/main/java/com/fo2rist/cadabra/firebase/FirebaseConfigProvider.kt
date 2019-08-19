package com.fo2rist.cadabra.firebase

import android.support.annotation.VisibleForTesting
import com.fo2rist.cadabra.ExperimentsConfigProvider
import com.fo2rist.cadabra.android.configFromJson
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue

/**
 * [ExperimentsConfigProvider] that starts experiments automatically by Firebase Remote Config.
 * Supports two ways for providing config.
 * 1. On the root level.
 * In that case all keys in Firebase that match names of registered experiments will be interpreted as experiments to be
 * activated and respective values as experiment Variants to activate.
 * E.g. For experiment `SomeTest` with variants `A`, `B` the remote config should have a key `SomeTest`
 * with value either `A` or `B`.
 *
 * 2. As nested Json.
 * In that case will looks for the specified key name in remote config and parse it as Json with a single object with
 * key:values pairs via [com.fo2rist.cadabra.android.configFromJson].
 */
class FirebaseConfigProvider : ExperimentsConfigProvider {
    private val rootElementKey: String?
    private val fetchAutomatically: Boolean
    private val useDefaults: Boolean
    private val firebaseConfig: FirebaseRemoteConfig

    /**
     * Create Firebase Config Provider.
     * Be default provider automatically fetches the default config and initiates the remote config loading.
     * If the automatic fetching is turned off or the remote config was updated [startExperimentFromRemoteConfig]
     * triggers re-loading.
     * @param fetchAutomatically automatically fetch and activate latest Firebase Config once attached to Cadabra
     * @param useDefaults automatically load default Firebase Config values once attached to Cadabra
     * @param rootElementKey name of the root element in Firebase Remote Config.
     *   If present the element with that key will be fetched from Remote config a single configuration JSON,
     *   otherwise will scan all keys in Remote config and match them with registered experiment names.
     */
    @JvmOverloads
    constructor(
        fetchAutomatically: Boolean = true,
        useDefaults: Boolean = true,
        rootElementKey: String? = null
    ) : this(fetchAutomatically, useDefaults, rootElementKey, FirebaseRemoteConfig.getInstance())

    @VisibleForTesting
    internal constructor(
        fetchAutomatically: Boolean,
        useDefaults: Boolean,
        rootElementKey: String?,
        firebaseConfig: FirebaseRemoteConfig
    ) {
        this.rootElementKey = rootElementKey
        this.fetchAutomatically = fetchAutomatically
        this.useDefaults = useDefaults
        this.firebaseConfig = firebaseConfig
    }

    override fun onAttached() {
        if (this.useDefaults) {
            startExperimentFromRemoteConfig()
        }

        if (this.fetchAutomatically) {
            firebaseConfig.fetch().addOnCompleteListener {
                // note that activateFetched won't be required after migration to firebase 17 that provides
                // fetchAndActivate method
                firebaseConfig.activateFetched()
                startExperimentFromRemoteConfig()
            }
        }
    }

    /**
     * Apply current active remote config.
     */
    fun startExperimentFromRemoteConfig() {
        val config = if (rootElementKey != null) {
            configFromJson(firebaseConfig.getString(rootElementKey))
        } else {
            configFromFirebaseConfig(firebaseConfig.all)
        }
        provideConfig(config)
    }

    //implementation of firebase v17 getAll function missed in v15
    private val FirebaseRemoteConfig.all: Map<String, FirebaseRemoteConfigValue>
        get() {
            return this.getKeysByPrefix("").associateWith { this.getValue(it) }
        }
}
