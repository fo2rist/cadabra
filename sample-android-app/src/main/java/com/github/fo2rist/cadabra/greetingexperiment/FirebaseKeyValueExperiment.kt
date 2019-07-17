package com.github.fo2rist.cadabra.greetingexperiment

import com.github.fo2rist.cadabra.Variant

/**
 * Firebase driven Experiment enum without embedded data that configured via FB key:values.
 * Demonstrates async experiment start with Firebase.
 * Accesses resource the same way [AutoResourceExperiment] does.
 */
enum class FirebaseKeyValueExperiment : Variant {
    KV0, KV1, KV2
}
