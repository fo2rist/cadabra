package com.fo2rist.cadabra.resolvers

import com.fo2rist.cadabra.testdata.SimpleExperiment
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class RandomResolverKotlinTest : WordSpec({

    val resolver = RandomResolver(SimpleExperiment::class)

    "variant" should {
        "return different results" {
            val oneVariant = resolver.variant
            var differentResultsObserved = false
            // some reasonable number of iteration to observe random values
            // potentially it's a flaky test but the probability of the failure is 2^-99
            for (i in 0..100) {
                if (resolver.variant != oneVariant) {
                    differentResultsObserved = true
                    break
                }
            }
            differentResultsObserved shouldBe true
        }
    }
})
