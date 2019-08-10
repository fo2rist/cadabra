package com.fo2rist.cadabra.resolvers

import com.fo2rist.cadabra.Resolver
import com.fo2rist.cadabra.Variant
import kotlin.random.Random
import kotlin.reflect.KClass

/**
 * [Resolver] that picks random variant every time asked.
 * Can be useful for testing.
 */
class RandomResolver<V>(
    private val variantsEnum: Class<V>
) : Resolver<V> where V : Variant {

    constructor(variantsEnum: KClass<V>) : this(variantsEnum.java)

    override val variant: V
        get() {
            val variants = variantsEnum.enumConstants
            return variants[Random.nextInt(variants.size)]
        }
}
