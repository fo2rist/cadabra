package com.github.fo2rist.cadabra

import io.kotlintest.matchers.beOfType
import io.kotlintest.matchers.types.shouldBeSameInstanceAs
import io.kotlintest.should
import io.kotlintest.specs.WordSpec

class CadabraKotlinTest : WordSpec({
    "Cadabra.instance" should {
        "return same CadabraImpl every time" {
            val instance = Cadabra.instance
            instance should beOfType(CadabraImpl::class)
            Cadabra.instance shouldBeSameInstanceAs instance
        }
    }

    "Cadabra.config" should {
        "return same CadabraImpl every time" {
            val config = Cadabra.config
            config should beOfType(CadabraImpl::class)
            Cadabra.config shouldBeSameInstanceAs config
        }
    }
})
