package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.exceptions.ExperimentNotFound
import com.github.fo2rist.cadabra.exceptions.ExperimentNotStarted
import com.github.fo2rist.cadabra.exceptions.UnknownVariant
import com.github.fo2rist.cadabra.exceptions.VariantNotFound
import com.github.fo2rist.cadabra.resolvers.StaticResolver
import io.kotlintest.TestCase
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec
import com.github.fo2rist.cadabra.SimpleExperiment as SimpleExperiment2
import com.github.fo2rist.cadabra.testdata.SimpleExperiment as SimpleExperiment1

private enum class SimpleExperiment : Variant { DEFAULT } //A class with name that duplicates the existing one.
private enum class EmptyExperiment : Variant

private val experiment1Id = SimpleExperiment1::class.experimentId
private val config1A = ExperimentsConfig.create(experiment1Id to SimpleExperiment1.A.name)
private val config1B = ExperimentsConfig.create(experiment1Id to SimpleExperiment1.B.name)
private val resolver1A = StaticResolver(SimpleExperiment1.A)


private lateinit var cadabra: CadabraImpl

class CadabraImplKotlinTest : WordSpec({

    "registerExperiment" should {

        "allow re-registration of experiment with the same ID" {
            cadabra.registerExperiment(SimpleExperiment1::class)

            shouldNotThrow<Exception> {
                cadabra.registerExperiment(SimpleExperiment2::class)
            }
        }
    }

    "startExperiment" should {

        "allow starting already registered experiment" {
            cadabra.registerExperiment(SimpleExperiment1::class)

            shouldNotThrow<Exception> {
                cadabra.startExperiment(SimpleExperiment1::class, resolver1A)
            }
        }

        "throw VariantNotFound if the experiment is incorrect or empty" {
            shouldThrow<VariantNotFound> {
                cadabra.startExperiment(EmptyExperiment::class)
            }
        }
    }

    "getExperimentVariant" should {

        "throw ExperimentNotFound when experiment not registered" {
            shouldThrow<ExperimentNotFound> {
                cadabra.getExperimentVariant(SimpleExperiment1::class)
            }
        }

        "throw ExperimentNotStarted when experiment registered but not started" {
            cadabra.registerExperiment(SimpleExperiment1::class)

            shouldThrow<ExperimentNotStarted> {
                cadabra.getExperimentVariant(SimpleExperiment1::class)
            }
        }

        "return given variant when experiment started" {
            cadabra.startExperiment(SimpleExperiment1::class, resolver1A)

            cadabra.getExperimentVariant(SimpleExperiment1::class) shouldBe SimpleExperiment1.A
        }

        "return given variant when experiment started later with startExperiments" {
            cadabra.registerExperiment(SimpleExperiment1::class)
            cadabra.startExperiments(config1A)

            cadabra.getExperimentVariant(SimpleExperiment1::class) shouldBe SimpleExperiment1.A
        }

        "return given variant when experiment started later with startExperimentsAsync" {
            val configProvider = object : ExperimentsConfigProvider() {}
            cadabra.registerExperiment(SimpleExperiment1::class)

            cadabra.startExperimentsAsync(configProvider)
            configProvider.provideConfig(config1A)

            cadabra.getExperimentVariant(SimpleExperiment1::class) shouldBe SimpleExperiment1.A
        }

        "return default variant if experiment started without explicit resolver" {
            cadabra.startExperiment(SimpleExperiment1::class)

            cadabra.getExperimentVariant(SimpleExperiment1::class) shouldBe SimpleExperiment1.A
        }

        "return latest applied variant if experiment restarted" {
            cadabra.registerExperiment(SimpleExperiment1::class)
            cadabra.startExperiments(config1A)
            cadabra.startExperiments(config1B)

            cadabra.getExperimentVariant(SimpleExperiment1::class) shouldBe SimpleExperiment1.B
        }
    }

    "startExperiments" should {

        "ignore unknown experiment IDs" {
            shouldNotThrow<Exception> {
                cadabra.startExperiments(ExperimentsConfig.create("DOES_NOT_EXIST" to SimpleExperiment1.A.name))
            }
        }

        "throw Exception on unknown variants" {
            cadabra.registerExperiment(SimpleExperiment1::class)

            shouldThrow<UnknownVariant> {
                cadabra.startExperiments(ExperimentsConfig.create(experiment1Id to "DOES_NOT_EXIST"))
            }
        }
    }

    "java classes" should {

        "be accepted by registerExperiment" {
            shouldNotThrow<Exception> {
                cadabra.registerExperiment(SimpleExperiment1::class.java)
            }
        }

        "be accepted by startExperiment" {
            shouldNotThrow<Exception> {
                cadabra.startExperiment(SimpleExperiment1::class.java, resolver1A)
            }
        }


        "be accepted by getExperimentVariant" {
            cadabra.startExperiment(SimpleExperiment1::class, resolver1A)

            cadabra.getExperimentVariant(SimpleExperiment1::class.java) shouldBe SimpleExperiment1.A
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        cadabra = CadabraImpl()
    }
}
