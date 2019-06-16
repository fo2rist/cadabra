package com.github.fo2rist.cadabraandroid

import io.kotlintest.matchers.beOfType
import io.kotlintest.matchers.types.shouldBeSameInstanceAs
import io.kotlintest.should
import io.kotlintest.specs.WordSpec

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
