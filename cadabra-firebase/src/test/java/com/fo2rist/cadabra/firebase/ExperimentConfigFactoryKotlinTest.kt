package com.fo2rist.cadabra.firebase

import com.fo2rist.cadabra.firebase.helpers.FakeFirebaseRemoteConfigValue
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

private const val CORRECT_KEY = "NotToBeIgnored"
private const val CORRECT_VALUE = "Value"
private val REMOTE_CONFIG = mapOf<String, FirebaseRemoteConfigValue?>(
    "ToBeIgnoredEmptyValue" to FakeFirebaseRemoteConfigValue(""),
    "ToBeIgnoredNullValue" to null,
    CORRECT_KEY to FakeFirebaseRemoteConfigValue(CORRECT_VALUE)
)

class ExperimentConfigFactoryKotlinTest : WordSpec({
    "configFromFirebaseConfig" should {

        "ignore null and empty values" {
            val config = configFromFirebaseConfig(REMOTE_CONFIG)

            config.entries.size shouldBe 1
        }

        "put correct values to config as is" {
            val config = configFromFirebaseConfig(REMOTE_CONFIG)

            config[CORRECT_KEY] shouldBe CORRECT_VALUE
        }
    }
})
