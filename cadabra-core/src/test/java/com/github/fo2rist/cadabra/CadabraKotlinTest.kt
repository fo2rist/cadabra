package com.github.fo2rist.cadabra

import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec

class CadabraKotlinTest : StringSpec({
    "can create instance" {
        Cadabra.instance shouldNotBe null
    }
})
