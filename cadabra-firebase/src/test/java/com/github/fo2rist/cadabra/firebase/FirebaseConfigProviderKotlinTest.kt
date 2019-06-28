package com.github.fo2rist.cadabra.firebase

import com.github.fo2rist.cadabra.firebase.helpers.ImmediateTask
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import io.kotlintest.TestCase
import io.kotlintest.specs.WordSpec
import org.mockito.Mockito

private const val CONFIG_KEY = "config_key"

class FirebaseConfigProviderKotlinTest : WordSpec() {
    private lateinit var firebaseConfigMock: FirebaseRemoteConfig

    override fun beforeTest(testCase: TestCase) {
        firebaseConfigMock = mock(defaultAnswer = Mockito.RETURNS_DEEP_STUBS) {
            on { getString(any()) }.thenReturn("")
            on { fetchAndActivate() }.thenReturn(ImmediateTask())
        }
    }

    init {
        "FirebaseConfigProvider constructor" should {

            "not fetch any config if not asked" {
                val configProvider = FirebaseConfigProvider(
                    configKey = CONFIG_KEY,
                    fetchAutomatically = false,
                    useDefaults = false,
                    firebaseConfig = firebaseConfigMock
                )

                verifyZeroInteractions(firebaseConfigMock)

                configProvider.onAttached()

                verifyZeroInteractions(firebaseConfigMock)
            }

            "use default config if asked but only after attached" {
                val configProvider = FirebaseConfigProvider(
                    configKey = CONFIG_KEY,
                    fetchAutomatically = false,
                    useDefaults = true,
                    firebaseConfig = firebaseConfigMock
                )
                verifyZeroInteractions(firebaseConfigMock)

                configProvider.onAttached()

                verify(firebaseConfigMock, never()).fetchAndActivate()
                verify(firebaseConfigMock).getString(CONFIG_KEY)
            }

            "fetch latest config if asked but only after attached" {
                val configProvider = FirebaseConfigProvider(
                    configKey = CONFIG_KEY,
                    fetchAutomatically = true,
                    useDefaults = false,
                    firebaseConfig = firebaseConfigMock
                )
                verifyZeroInteractions(firebaseConfigMock)

                configProvider.onAttached()

                verify(firebaseConfigMock).fetchAndActivate()
                verify(firebaseConfigMock).getString(CONFIG_KEY)
            }
        }

        "activateExperimentFromRemoteConfig" should {

            "fetch the current config" {
                val configProvider = FirebaseConfigProvider(
                    configKey = CONFIG_KEY,
                    fetchAutomatically = false,
                    useDefaults = false,
                    firebaseConfig = firebaseConfigMock
                )

                verifyZeroInteractions(firebaseConfigMock)

                configProvider.activateExperimentFromRemoteConfig()

                verify(firebaseConfigMock).getString(CONFIG_KEY)
                verify(firebaseConfigMock, never()).fetchAndActivate()
            }
        }
    }
}
