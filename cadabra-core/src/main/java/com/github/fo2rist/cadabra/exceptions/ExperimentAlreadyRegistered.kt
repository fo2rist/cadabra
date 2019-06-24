package com.github.fo2rist.cadabra.exceptions

/**
 * Registration of experiment with duplicating ID attempt.
 */
class ExperimentAlreadyRegistered(message: String) : IllegalStateException(message)
