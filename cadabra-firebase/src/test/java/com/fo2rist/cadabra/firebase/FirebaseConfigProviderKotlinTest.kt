package com.fo2rist.cadabra.firebase

import com.fo2rist.cadabra.firebase.helpers.ImmediateTask
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import io.kotlintest.TestCase
import io.kotlintest.specs.WordSpec
import org.mockito.Mockito

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

            "not fetch any config when fetchAutomatically==false, useDefaults==false" {
                val configProvider = FirebaseConfigProvider(
                    fetchAutomatically = false,
                    useDefaults = false,
                    rootElementKey = null,
                    firebaseConfig = firebaseConfigMock
                )

                configProvider.onAttached()

                verifyZeroInteractions(firebaseConfigMock)
            }

            "use default config after attached when useDefaults==true" {
                val configProvider = FirebaseConfigProvider(
                    fetchAutomatically = false,
                    useDefaults = true,
                    rootElementKey = null,
                    firebaseConfig = firebaseConfigMock
                )
                verifyZeroInteractions(firebaseConfigMock)

                configProvider.onAttached()

                verify(firebaseConfigMock, never()).fetchAndActivate()
                verify(firebaseConfigMock).all
            }

            "fetch latest config after attached when fetchAutomatically==true" {
                val configProvider = FirebaseConfigProvider(
                    fetchAutomatically = true,
                    useDefaults = false,
                    rootElementKey = null,
                    firebaseConfig = firebaseConfigMock
                )
                verifyZeroInteractions(firebaseConfigMock)

                configProvider.onAttached()

                verify(firebaseConfigMock).fetchAndActivate()
                verify(firebaseConfigMock).all
            }
        }

        "startExperimentFromRemoteConfig" should {

            "fetch the current config as Key:Value when configKey==null" {
                val configProvider = FirebaseConfigProvider(
                    fetchAutomatically = false,
                    useDefaults = false,
                    rootElementKey = null,
                    firebaseConfig = firebaseConfigMock
                )

                configProvider.startExperimentFromRemoteConfig()

                verify(firebaseConfigMock).all
                verifyNoMoreInteractions(firebaseConfigMock)
            }

            "fetch the current config as Json when configKey!=null" {
                val rootElementKey = "dummy"
                val configProvider = FirebaseConfigProvider(
                    fetchAutomatically = false,
                    useDefaults = false,
                    rootElementKey = rootElementKey,
                    firebaseConfig = firebaseConfigMock
                )

                configProvider.startExperimentFromRemoteConfig()

                verify(firebaseConfigMock).getString(rootElementKey)
                verifyNoMoreInteractions(firebaseConfigMock)
            }
        }
    }
}
