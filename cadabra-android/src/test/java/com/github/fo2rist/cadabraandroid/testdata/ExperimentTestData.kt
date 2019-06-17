package com.github.fo2rist.cadabraandroid.testdata

import com.github.fo2rist.cadabra.Resolver
import com.github.fo2rist.cadabra.Variant

enum class SimpleAndroidExperiment : Variant {
    A, B
}

class SimpleAndroidStaticResolver(
    override var variant: SimpleAndroidExperiment = SimpleAndroidExperiment.A
) : Resolver<SimpleAndroidExperiment>
