package com.github.fo2rist.cadabra.testdata

import com.github.fo2rist.cadabra.Resolver
import com.github.fo2rist.cadabra.Variant

enum class SimpleExperiment : Variant {
    A, B
}

class SimpleStaticResolver(
    override var variant: SimpleExperiment = SimpleExperiment.A
) : Resolver<SimpleExperiment>
