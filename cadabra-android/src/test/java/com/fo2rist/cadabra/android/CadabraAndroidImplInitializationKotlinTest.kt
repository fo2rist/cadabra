package com.fo2rist.cadabra.android

import com.fo2rist.cadabra.android.exceptions.NotInitializedException
import com.fo2rist.cadabra.android.testdata.SimpleAndroidExperiment
import com.fo2rist.cadabra.android.testdata.createAppContextMock
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.WordSpec

class CadabraAndroidImplInitializationKotlinTest : WordSpec({

    val cadabraAndroidImpl = CadabraAndroidImpl()

    "CadabraAndroid" should {
        "throw exception if not initialized on getExperimentContext" {
            shouldThrow<NotInitializedException> {
                cadabraAndroidImpl.getExperimentContext(SimpleAndroidExperiment::class)
            }
        }

        "ignore double initialization" {
            cadabraAndroidImpl.initialize(createAppContextMock())

            shouldNotThrow<Exception> {
                cadabraAndroidImpl.initialize(createAppContextMock())
            }
        }
    }
})
