package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabraandroid.exceptions.NotInitializedException
import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidExperiment
import com.github.fo2rist.cadabraandroid.testdata.createAppContextMock
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
