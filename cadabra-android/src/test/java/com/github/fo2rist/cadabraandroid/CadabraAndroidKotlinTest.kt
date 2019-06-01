package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabra.Cadabra
import io.kotlintest.matchers.types.shouldBeSameInstanceAs
import io.kotlintest.specs.WordSpec

class CadabraAndroidKotlinTest : WordSpec({
    "CadabraAndroid.instance" should {
        "return CadabraAndroidImpl" {
            CadabraAndroid.instance shouldBeSameInstanceAs CadabraAndroidImpl
        }
    }

    "CadabraAndroid.config" should {
        "return Cadabra.config" {
            CadabraAndroid.config shouldBeSameInstanceAs Cadabra.config
        }
    }
})
