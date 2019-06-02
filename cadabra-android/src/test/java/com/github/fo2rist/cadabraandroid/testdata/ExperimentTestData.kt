package com.github.fo2rist.cadabraandroid.testdata

import com.github.fo2rist.cadabra.Resolver
import com.github.fo2rist.cadabra.Variant
import com.github.fo2rist.cadabra.VariantId

enum class SimpleAndroidVariants(
    override val id: VariantId
) : Variant {
    A("a"),
    B("b"),
    ;
}

class SimpleAndroidStaticResolver(
    override var variant: SimpleAndroidVariants = SimpleAndroidVariants.A
) : Resolver<SimpleAndroidVariants>
