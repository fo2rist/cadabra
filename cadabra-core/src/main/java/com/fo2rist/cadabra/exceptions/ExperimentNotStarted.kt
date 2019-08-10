package com.fo2rist.cadabra.exceptions

/**
 * Experiment is registered but not started.
 * @see [com.fo2rist.cadabra.CadabraConfig.startExperiment]
 * @see [com.fo2rist.cadabra.CadabraConfig.startExperiments]
 */
class ExperimentNotStarted(message: String) : IllegalStateException(message)
