package com.fo2rist.cadabra

import io.kotlintest.shouldNotBe
import org.spekframework.spek2.Spek

class CadabraSpek : Spek({
    group("construction") {
        test("can create instance") {
            val cadabra = Cadabra.instance
            cadabra shouldNotBe null
        }
    }
})
