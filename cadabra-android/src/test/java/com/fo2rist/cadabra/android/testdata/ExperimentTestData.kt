package com.fo2rist.cadabra.android.testdata

import com.fo2rist.cadabra.Resolver
import com.fo2rist.cadabra.Variant

enum class SimpleAndroidExperiment : Variant {
    A, B
}

class SimpleAndroidStaticResolver(
    override var variant: SimpleAndroidExperiment = SimpleAndroidExperiment.A
) : Resolver<SimpleAndroidExperiment>
