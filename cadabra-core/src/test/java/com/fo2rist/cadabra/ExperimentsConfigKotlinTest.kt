package com.fo2rist.cadabra

import com.fo2rist.cadabra.testdata.SimpleExperiment
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.core.spec.style.WordSpec

val EXPERIMENT_ID = SimpleExperiment::class.experimentId
val VARIANT_NAME = SimpleExperiment.A.name

class ExperimentsConfigKotlinTest : WordSpec({

    val singleItemConfig = ExperimentsConfig.create(EXPERIMENT_ID to VARIANT_NAME)

    "create" should {
        "produce same result for pairs and map argument" {
            singleItemConfig.entries shouldBe ExperimentsConfig.create(mapOf(EXPERIMENT_ID to VARIANT_NAME)).entries
        }
    }

    "operator get" should {
        "return non null for existing experiment" {
            singleItemConfig[EXPERIMENT_ID] shouldNotBe null
        }

        "return null for unknown experiment" {
            singleItemConfig["DOES_NOT_EXIST"] shouldBe null
        }
    }

    "entries" should {
        "give full list of existing experiment variant pairs" {
            singleItemConfig.entries.size shouldBe 1
            singleItemConfig.entries.first().key shouldBe EXPERIMENT_ID
            singleItemConfig.entries.first().value shouldBe VARIANT_NAME
        }
    }
})
