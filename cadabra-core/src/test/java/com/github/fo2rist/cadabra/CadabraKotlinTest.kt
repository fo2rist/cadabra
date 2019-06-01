package com.github.fo2rist.cadabra

import io.kotlintest.matchers.types.shouldBeSameInstanceAs
import io.kotlintest.specs.WordSpec

class CadabraKotlinTest : WordSpec({
    "Cadabra.instance" should {
        "return CadabraImpl" {
            Cadabra.instance shouldBeSameInstanceAs CadabraImpl
        }
    }

    "Cadabra.config" should {
        "return CadabraImpl" {
            Cadabra.config shouldBeSameInstanceAs CadabraImpl
        }
    }
})
