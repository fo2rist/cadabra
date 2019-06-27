package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.exceptions.ExperimentAlreadyRegistered
import com.github.fo2rist.cadabra.exceptions.ExperimentNotActive
import com.github.fo2rist.cadabra.exceptions.ExperimentNotFound
import com.github.fo2rist.cadabra.exceptions.UnknownVariant
import com.github.fo2rist.cadabra.resolvers.StaticResolver
import io.kotlintest.TestCase
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec
import com.github.fo2rist.cadabra.SimpleExperiment as SimpleExperiment2
import com.github.fo2rist.cadabra.testdata.SimpleExperiment as SimpleExperiment1

//A class with name that duplicates the existing one.
private enum class SimpleExperiment : Variant { DEFAULT }

private val experiment1Id = SimpleExperiment1::class.experimentId
private val config1A = ExperimentsConfig.create(experiment1Id to SimpleExperiment1.A.name)
private val config1B = ExperimentsConfig.create(experiment1Id to SimpleExperiment1.B.name)
private val resolver1A = StaticResolver(SimpleExperiment1.A)
private val resolver2 = StaticResolver(SimpleExperiment2.DEFAULT)

private lateinit var cadabra: CadabraImpl

class CadabraImplKotlinTest : WordSpec({

    "registerExperiment" should {

        "not register experiments with the same ID" {
            cadabra.registerExperiment(SimpleExperiment1::class)

            shouldThrow<ExperimentAlreadyRegistered> {
                cadabra.registerExperiment(SimpleExperiment2::class)
            }
        }

        "accept Java classes" {
            shouldNotThrow<Exception> {
                cadabra.registerExperiment(SimpleExperiment1::class.java)
            }
        }
    }

    "startExperiment" should {

        "not start already registered experiment" {
            cadabra.registerExperiment(SimpleExperiment1::class)

            shouldThrow<ExperimentAlreadyRegistered> {
                cadabra.startExperiment(SimpleExperiment1::class, resolver1A)
            }
        }

        "not register experiments with the same ID" {
            cadabra.startExperiment(SimpleExperiment1::class, resolver1A)

            shouldThrow<ExperimentAlreadyRegistered> {
                cadabra.startExperiment(SimpleExperiment2::class, resolver2)
            }
        }

        "accept Java classes" {
            shouldNotThrow<Exception> {
                cadabra.startExperiment(SimpleExperiment1::class.java, resolver1A)
            }
        }
    }

    "getExperimentVariant" should {

        "throw ExperimentNotFound when experiment not registered" {
            shouldThrow<ExperimentNotFound> {
                cadabra.getExperimentVariant(SimpleExperiment1::class)
            }
        }

        "throw ExperimentNotActive when experiment registered but not active" {
            cadabra.registerExperiment(SimpleExperiment1::class)

            shouldThrow<ExperimentNotActive> {
                cadabra.getExperimentVariant(SimpleExperiment1::class)
            }
        }

        "return variant when experiment started via Kotlin class" {
            cadabra.startExperiment(SimpleExperiment1::class, resolver1A)

            cadabra.getExperimentVariant(SimpleExperiment1::class) shouldBe SimpleExperiment1.A
        }

        "return variant when experiment started via Java class" {
            cadabra.startExperiment(SimpleExperiment1::class.java, resolver1A)

            cadabra.getExperimentVariant(SimpleExperiment1::class.java) shouldBe SimpleExperiment1.A
        }

        "return variant when experiment activated later with activateExperiments" {
            cadabra.registerExperiment(SimpleExperiment1::class)
            cadabra.activateExperiments(config1A)

            cadabra.getExperimentVariant(SimpleExperiment1::class) shouldBe SimpleExperiment1.A
        }

        "return latest activated variant" {
            cadabra.registerExperiment(SimpleExperiment1::class)
            cadabra.activateExperiments(config1A)
            cadabra.activateExperiments(config1B)

            cadabra.getExperimentVariant(SimpleExperiment1::class) shouldBe SimpleExperiment1.B
        }

        "return variant when experiment activated later with activateExperimentsAsync" {
            val configProvider = object : ExperimentsConfigProvider() {}
            cadabra.registerExperiment(SimpleExperiment1::class)

            cadabra.activateExperimentsAsync(configProvider)
            configProvider.provideConfig(config1A)

            cadabra.getExperimentVariant(SimpleExperiment1::class) shouldBe SimpleExperiment1.A
        }
    }

    "activateExperiments" should {

        "ignore unknown experiment IDs" {
            shouldNotThrow<Exception> {
                cadabra.activateExperiments(ExperimentsConfig.create("DOES_NOT_EXIST" to SimpleExperiment1.A.name))
            }
        }

        "throw Exception on unknown variants" {
            cadabra.registerExperiment(SimpleExperiment1::class)

            shouldThrow<UnknownVariant> {
                cadabra.activateExperiments(ExperimentsConfig.create(experiment1Id to "DOES_NOT_EXIST"))
            }
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        cadabra = CadabraImpl()
    }
}
