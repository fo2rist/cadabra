package com.fo2rist.cadabra.resolvers

import com.fo2rist.cadabra.testdata.SimpleExperiment
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec

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
