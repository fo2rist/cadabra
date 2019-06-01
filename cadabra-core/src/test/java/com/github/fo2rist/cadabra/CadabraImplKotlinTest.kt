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

class CadabraImplKotlinTest : WordSpec({
    "registerExperiment(Experiment, Resolver)" should {
        "not register the same experiment twice" {
            CadabraImpl.registerExperiment(EXPERIMENT1, SimpleStaticResolver())

            shouldThrow<IllegalStateException> {
                CadabraImpl.registerExperiment(EXPERIMENT1, SimpleStaticResolver())
            }
        }

        "not register experiments with the same ID" {
            CadabraImpl.registerExperiment(EXPERIMENT1, SimpleStaticResolver())

            shouldThrow<IllegalStateException> {
                CadabraImpl.registerExperiment(EXPERIMENT2, SimpleStaticResolver())
            }
        }
    }

    "registerExperiment(Variant, Resolver)" should {
        "not register experiments with the same variants" {
            CadabraImpl.registerExperiment(SimpleVariants::class, SimpleStaticResolver())

            shouldThrow<IllegalStateException> {
                CadabraImpl.registerExperiment(SimpleVariants::class, SimpleStaticResolver())
            }
        }

        "accept Java class as variants" {
            shouldNotThrow<Exception> {
                CadabraImpl.registerExperiment(SimpleVariants::class.java, SimpleStaticResolver())
            }
        }
    }

    "getExperimentVariant" should {

        "throw exception when experiment not registered" {
            shouldThrow<java.lang.IllegalStateException> {
                CadabraImpl.getExperimentVariant(EXPERIMENT1)
            }
        }

        "return variant when experiment registered via Experiment instance" {
            CadabraImpl.registerExperiment(EXPERIMENT1, SimpleStaticResolver())

            CadabraImpl.getExperimentVariant(EXPERIMENT1) should beOfType<SimpleVariants>()
        }

        "return variant when experiment registered via Variant class" {
            CadabraImpl.registerExperiment(SimpleVariants::class, SimpleStaticResolver())

            CadabraImpl.getExperimentVariant(SimpleVariants::class) should beOfType<SimpleVariants>()
        }

        "return variant when experiment registered via Variant Java class" {
            CadabraImpl.registerExperiment(SimpleVariants::class.java, SimpleStaticResolver())

            CadabraImpl.getExperimentVariant(SimpleVariants::class.java) should beOfType<SimpleVariants>()
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        CadabraImpl.reset()
    }
}
