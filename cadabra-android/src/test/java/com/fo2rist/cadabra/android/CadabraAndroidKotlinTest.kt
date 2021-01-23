package com.fo2rist.cadabra.android

import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.should
import io.kotest.matchers.types.beOfType

class CadabraAndroidKotlinTest : WordSpec({
    "CadabraAndroid.instance" should {
        "return CadabraAndroidImpl" {
            val instance = CadabraAndroid.instance
            instance should beOfType<CadabraAndroidImpl>()
            CadabraAndroid.instance shouldBeSameInstanceAs instance
        }
    }

    "CadabraAndroid.config" should {
        "return CadabraAndroidImpl" {
            val config = CadabraAndroid.config
            config should beOfType<CadabraAndroidImpl>()
            CadabraAndroid.config shouldBeSameInstanceAs config
        }
    }
})
