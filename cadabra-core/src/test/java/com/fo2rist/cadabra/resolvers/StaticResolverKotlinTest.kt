package com.fo2rist.cadabra.resolvers

import com.fo2rist.cadabra.testdata.SimpleExperiment
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class StaticResolverKotlinTest : WordSpec({

    val staticResolverA = StaticResolver(SimpleExperiment.A)
    val staticResolverB = StaticResolver(SimpleExperiment.B)

    "variant" should {
        "always return same variant" {
            staticResolverA.variant shouldBe  SimpleExperiment.A
            staticResolverB.variant shouldBe  SimpleExperiment.B
        }
    }
})
