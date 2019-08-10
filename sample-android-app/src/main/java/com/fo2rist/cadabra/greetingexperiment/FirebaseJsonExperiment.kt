package com.fo2rist.cadabra.greetingexperiment

import com.fo2rist.cadabra.Variant

/**
 * Firebase driven Experiment enum without embedded data that configured via Json object.
 * Demonstrates async experiment start with Firebase.
 * Accesses resource the same way [AutoResourceExperiment] does.
 */
enum class FirebaseJsonExperiment : Variant {
    F1, F2
}
