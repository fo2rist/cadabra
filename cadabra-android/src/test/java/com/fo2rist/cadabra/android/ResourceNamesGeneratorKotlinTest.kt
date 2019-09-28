package com.fo2rist.cadabra.android

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class ResourceNamesGeneratorKotlinTest : WordSpec({

    val namesGenerator = ResourceNamesGenerator()

    "generateResourceName" should {
        "return the same name if suffix matches variant name" {
            namesGenerator.generateResourceName("string_a", "a") shouldBe "string_a"
        }

        "return the same name if variant name is null" {
            namesGenerator.generateResourceName("string_a", null) shouldBe "string_a"
        }

        "replace suffix if it's different from variant name" {
            namesGenerator.generateResourceName("string_a", "b") shouldBe "string_b"
        }
    }
})
