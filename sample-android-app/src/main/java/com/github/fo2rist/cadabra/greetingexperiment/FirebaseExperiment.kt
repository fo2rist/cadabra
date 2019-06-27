package com.github.fo2rist.cadabra.greetingexperiment

import com.github.fo2rist.cadabra.Variant

/**
 * Firebase driven Experiment enum without embedded data.
 * Demonstrates async experiment activation with Firebase.
 * Accesses resource the same way [AutoResourceExperiment] does.
 */
enum class FirebaseExperiment : Variant {
    F1, F2
}
