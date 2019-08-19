package com.fo2rist.cadabra.android

import com.fo2rist.cadabra.android.exceptions.NotInitializedException
import com.fo2rist.cadabra.android.testdata.SimpleAndroidExperiment
import com.fo2rist.cadabra.android.testdata.createAppContextMock
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

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
