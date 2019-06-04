package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidVariants
import com.github.fo2rist.cadabraandroid.testdata.createAppContextMock
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

class CadabraAndroidImplInitializationKotlinTest : WordSpec({

    val cadabraAndroidImpl = CadabraAndroidImpl()

    "CadabraAndroid" should {
        "throw exception if not initialized on getExperimentContext" {
            //TODO register the experiment here to make sure it throws exception b/c not initialized, not b/c it's
            //not found. Maybe change the other exception to not found

            shouldThrow<IllegalStateException> {
                cadabraAndroidImpl.getExperimentContext(SimpleAndroidVariants::class)
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
