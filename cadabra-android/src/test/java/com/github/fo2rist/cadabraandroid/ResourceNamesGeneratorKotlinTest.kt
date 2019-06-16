package com.github.fo2rist.cadabraandroid

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class ResourceNamesGeneratorKotlinTest : WordSpec({

    val namesGenerator = ResourceNamesGenerator()

    "generateResourceName" should {
        "return the same name if suffix matches variant name" {
            namesGenerator.generateResourceName("string_a", "a") shouldBe "string_a"
        }

        "replace suffix if it doesn't match variant name" {
            namesGenerator.generateResourceName("string_a", "b") shouldBe "string_b"
        }
    }
})
