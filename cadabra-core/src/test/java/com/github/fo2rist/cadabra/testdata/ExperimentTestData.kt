package com.github.fo2rist.cadabra.testdata

import com.github.fo2rist.cadabra.BaseExperiment
import com.github.fo2rist.cadabra.Resolver
import com.github.fo2rist.cadabra.Variant

enum class SimpleVariants(
    override val id: String
) : Variant {
    A("a"),
    B("b"),
    ;
}

class SimpleExperiment : BaseExperiment<SimpleVariants>(SimpleVariants::class)

class SimpleStaticResolver(
    override var variant: SimpleVariants = SimpleVariants.A
) : Resolver<SimpleVariants>
