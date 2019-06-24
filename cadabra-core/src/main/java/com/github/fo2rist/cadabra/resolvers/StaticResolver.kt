package com.github.fo2rist.cadabra.resolvers

import com.github.fo2rist.cadabra.Resolver
import com.github.fo2rist.cadabra.Variant

/**
 * [Resolver] that returns the same variant one constructed.
 * Can be useful when the variant is defined for the lifetime of the app or for testing.
 */
class StaticResolver<V>(
    override val variant: V
) : Resolver<V> where V : Variant
