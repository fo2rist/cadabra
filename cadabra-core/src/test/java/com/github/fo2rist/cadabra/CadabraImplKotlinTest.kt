package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.exceptions.ExperimentAlreadyRegistered
import com.github.fo2rist.cadabra.exceptions.ExperimentNotFound
import com.github.fo2rist.cadabra.resolvers.StaticResolver
import io.kotlintest.TestCase
import io.kotlintest.matchers.beOfType
import io.kotlintest.should
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec
import com.github.fo2rist.cadabra.SimpleExperiment as SimpleExperiment2
import com.github.fo2rist.cadabra.testdata.SimpleExperiment as SimpleExperiment1

//A class with name that duplicates the existing one.
private enum class SimpleExperiment : Variant { DEFAULT }

private val resolver1 = StaticResolver(SimpleExperiment1.A)
private val resolver2 = StaticResolver(SimpleExperiment2.DEFAULT)

private lateinit var cadabra: CadabraImpl

class CadabraImplKotlinTest : WordSpec({

    "registerExperiment" should {

        "not register the same experiment twice" {
            cadabra.registerExperiment(SimpleExperiment1::class, resolver1)

            shouldThrow<ExperimentAlreadyRegistered> {
                cadabra.registerExperiment(SimpleExperiment1::class, resolver1)
            }
        }

        "not register experiments with the same ID" {
            cadabra.registerExperiment(SimpleExperiment1::class, resolver1)

            shouldThrow<ExperimentAlreadyRegistered> {
                cadabra.registerExperiment(SimpleExperiment2::class, resolver2)
            }
        }

        "accept Java class as variants" {
            shouldNotThrow<Exception> {
                cadabra.registerExperiment(SimpleExperiment1::class.java, resolver1)
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
            cadabra.registerExperiment(SimpleExperiment1::class, resolver1)

            cadabra.getExperimentVariant(SimpleExperiment1::class) should beOfType<SimpleExperiment1>()
        }

        "return variant when experiment registered via Java class" {
            cadabra.registerExperiment(SimpleExperiment1::class.java, resolver1)

            cadabra.getExperimentVariant(SimpleExperiment1::class.java) should beOfType<SimpleExperiment1>()
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        cadabra = CadabraImpl()
    }
}
