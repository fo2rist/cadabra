package com.fo2rist.cadabra.firebase

import com.fo2rist.cadabra.ExperimentsConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue

/**
 * Create config from <experiment_id: variant_name> map.
 * Interprets each [FirebaseRemoteConfigValue] as a strings ignoring nulls and empty values.
 */
fun configFromFirebaseConfig(map: Map<String, FirebaseRemoteConfigValue>): ExperimentsConfig {
    return ExperimentsConfig.create(
        map.mapValues { it.value.asString() }
            .filter { !it.value.isNullOrEmpty() }
    )
}
