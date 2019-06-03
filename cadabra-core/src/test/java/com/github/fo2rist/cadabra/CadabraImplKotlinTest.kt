package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.testdata.SimpleExperiment
import com.github.fo2rist.cadabra.testdata.SimpleStaticResolver
import com.github.fo2rist.cadabra.testdata.SimpleVariants
import io.kotlintest.TestCase
import io.kotlintest.matchers.beOfType
import io.kotlintest.should
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

private val EXPERIMENT1 = SimpleExperiment()
private val EXPERIMENT2 = SimpleExperiment()

private lateinit var cadabra: CadabraImpl

class CadabraImplKotlinTest : WordSpec({
    "registerExperiment(Experiment, Resolver)" should {
        "not register the same experiment twice" {
            cadabra.registerExperiment(EXPERIMENT1, SimpleStaticResolver())

            shouldThrow<IllegalStateException> {
                cadabra.registerExperiment(EXPERIMENT1, SimpleStaticResolver())
            }
        }

        "not register experiments with the same ID" {
            cadabra.registerExperiment(EXPERIMENT1, SimpleStaticResolver())

            shouldThrow<IllegalStateException> {
                cadabra.registerExperiment(EXPERIMENT2, SimpleStaticResolver())
            }
        }
    }

    "registerExperiment(Variant, Resolver)" should {
        "not register experiments with the same variants" {
            cadabra.registerExperiment(SimpleVariants::class, SimpleStaticResolver())

            shouldThrow<IllegalStateException> {
                cadabra.registerExperiment(SimpleVariants::class, SimpleStaticResolver())
            }
        }

        "accept Java class as variants" {
            shouldNotThrow<Exception> {
                cadabra.registerExperiment(SimpleVariants::class.java, SimpleStaticResolver())
            }
        }
    }

    "getExperimentVariant" should {

        "throw exception when experiment not registered" {
            shouldThrow<java.lang.IllegalStateException> {
                cadabra.getExperimentVariant(EXPERIMENT1)
            }
        }

        "return variant when experiment registered via Experiment instance" {
            cadabra.registerExperiment(EXPERIMENT1, SimpleStaticResolver())

            cadabra.getExperimentVariant(EXPERIMENT1) should beOfType<SimpleVariants>()
        }

        "return variant when experiment registered via Variant class" {
            cadabra.registerExperiment(SimpleVariants::class, SimpleStaticResolver())

            cadabra.getExperimentVariant(SimpleVariants::class) should beOfType<SimpleVariants>()
        }

        "return variant when experiment registered via Variant Java class" {
            cadabra.registerExperiment(SimpleVariants::class.java, SimpleStaticResolver())

            cadabra.getExperimentVariant(SimpleVariants::class.java) should beOfType<SimpleVariants>()
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        cadabra = CadabraImpl()
    }
}
