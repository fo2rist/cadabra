package com.fo2rist.cadabra

import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.should
import io.kotest.matchers.types.beOfType

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
