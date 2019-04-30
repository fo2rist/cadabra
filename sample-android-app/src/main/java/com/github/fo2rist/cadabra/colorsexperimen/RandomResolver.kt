package com.github.fo2rist.cadabra.colorsexperimen

import com.github.fo2rist.cadabra.Resolver
import com.github.fo2rist.cadabra.Variant
import kotlin.random.Random
import kotlin.reflect.KClass

/**
 * Picks random variant every time asked.
 */
class RandomResolver<V>(
    private val variantsEnum: Class<V>
) : Resolver<V> where V : Variant, V : Enum<V> {

    constructor(variantsEnum: KClass<V>) : this(variantsEnum.java)

    override val variant: V
        get() {
            val variants = variantsEnum.enumConstants
            return variants[Random.nextInt(variants.size)]
        }
}
