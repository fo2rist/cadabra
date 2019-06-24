package com.github.fo2rist.cadabra.exceptions

/**
 * Access of experiment by unknown ID attempt.
 */
class ExperimentNotFound(message: String) : IllegalStateException(message)
