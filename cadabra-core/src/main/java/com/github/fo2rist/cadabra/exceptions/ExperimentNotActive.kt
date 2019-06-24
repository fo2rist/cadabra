package com.github.fo2rist.cadabra.exceptions

/**
 * Experiment is registered but not active.
 * @see [com.github.fo2rist.cadabra.CadabraConfig.activateExperiments]
 * @see [com.github.fo2rist.cadabra.CadabraConfig.startExperiment]
 */
class ExperimentNotActive(message: String) : IllegalStateException(message)
