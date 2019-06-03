package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabra.Cadabra
import io.kotlintest.matchers.beOfType
import io.kotlintest.matchers.types.shouldBeSameInstanceAs
import io.kotlintest.should
import io.kotlintest.specs.WordSpec

class CadabraAndroidKotlinTest : WordSpec({
    "CadabraAndroid.instance" should {
        "return CadabraAndroidImpl" {
            val instance = CadabraAndroid.instance
            instance should beOfType(CadabraAndroidImpl::class)
            CadabraAndroid.instance shouldBeSameInstanceAs instance
        }
    }

    "CadabraAndroid.config" should {
        "return Cadabra.config" {
            CadabraAndroid.config shouldBeSameInstanceAs Cadabra.config
        }
    }
})
