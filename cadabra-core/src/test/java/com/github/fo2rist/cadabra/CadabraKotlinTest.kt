package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.testdata.SimpleExperiment
import com.github.fo2rist.cadabra.testdata.SimpleStaticResolver
import com.github.fo2rist.cadabra.testdata.SimpleVariants
import io.kotlintest.TestCase
import io.kotlintest.matchers.beOfType
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

private val EXPERIMENT1 = SimpleExperiment()
private val EXPERIMENT2 = SimpleExperiment()

class CadabraKotlinTest : WordSpec({
    "Cadabra.instance" should {
        "returns CadabraImpl" {
            Cadabra.instance shouldBe CadabraImpl
        }
    }

    "Cadabra.Config" should {
        "not register the same experiment twice" {

            Cadabra.Config.registerExperiment(EXPERIMENT1, SimpleStaticResolver())

            shouldThrow<IllegalStateException> {
                Cadabra.Config.registerExperiment(EXPERIMENT1, SimpleStaticResolver())
            }
        }

        "not register experiments with the same ID" {

            Cadabra.Config.registerExperiment(EXPERIMENT1, SimpleStaticResolver())

            shouldThrow<IllegalStateException> {
                Cadabra.Config.registerExperiment(EXPERIMENT2, SimpleStaticResolver())
            }
        }
    }

    "Cadabra getExperimentVariant" should {
        "throw exception when experiment not registered" {
            shouldThrow<java.lang.IllegalStateException> {
                Cadabra.instance.getExperimentVariant(EXPERIMENT1)
            }
        }

        "return variant when experiment registered" {
            Cadabra.Config.registerExperiment(EXPERIMENT1, SimpleStaticResolver())

            Cadabra.instance.getExperimentVariant(EXPERIMENT1) should beOfType<SimpleVariants>()
        }

    }
}) {
    override fun beforeTest(testCase: TestCase) {
        CadabraImpl.reset()
    }
}
