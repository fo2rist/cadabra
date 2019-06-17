package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.exceptions.ExperimentAlreadyRegistered
import com.github.fo2rist.cadabra.exceptions.ExperimentNotFound
import io.kotlintest.TestCase
import io.kotlintest.matchers.beOfType
import io.kotlintest.should
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec
import com.github.fo2rist.cadabra.SimpleExperiment as SimpleExperiment2
import com.github.fo2rist.cadabra.testdata.SimpleStaticResolver as SimpleStaticResolver1
import com.github.fo2rist.cadabra.testdata.SimpleExperiment as SimpleExperiment1

//A class with simple name that duplicates the existing one.
private enum class SimpleExperiment : Variant { DEFAULT }
private class SimpleStaticResolver2(
    override var variant: SimpleExperiment2 = SimpleExperiment2.DEFAULT
) : Resolver<SimpleExperiment2>

private lateinit var cadabra: CadabraImpl

class CadabraImplKotlinTest : WordSpec({
    "registerExperiment" should {

        "not register the same experiment twice" {
            cadabra.registerExperiment(SimpleExperiment1::class, SimpleStaticResolver1())

            shouldThrow<ExperimentAlreadyRegistered> {
                cadabra.registerExperiment(SimpleExperiment1::class, SimpleStaticResolver1())
            }
        }

        "not register experiments with the same ID" {
            cadabra.registerExperiment(SimpleExperiment1::class, SimpleStaticResolver1())

            shouldThrow<ExperimentAlreadyRegistered> {
                cadabra.registerExperiment(SimpleExperiment2::class, SimpleStaticResolver2())
            }
        }

        "accept Java class as variants" {
            shouldNotThrow<Exception> {
                cadabra.registerExperiment(SimpleExperiment1::class.java, SimpleStaticResolver1())
            }
        }
    }

    "getExperimentVariant" should {

        "throw exception when experiment not registered" {
            shouldThrow<ExperimentNotFound> {
                cadabra.getExperimentVariant(SimpleExperiment1::class)
            }
        }

        "return variant when experiment registered via Kotlin class" {
            cadabra.registerExperiment(SimpleExperiment1::class, SimpleStaticResolver1())

            cadabra.getExperimentVariant(SimpleExperiment1::class) should beOfType<SimpleExperiment1>()
        }

        "return variant when experiment registered via Java class" {
            cadabra.registerExperiment(SimpleExperiment1::class.java, SimpleStaticResolver1())

            cadabra.getExperimentVariant(SimpleExperiment1::class.java) should beOfType<SimpleExperiment1>()
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        cadabra = CadabraImpl()
    }
}
