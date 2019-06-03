package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidVariants
import com.github.fo2rist.cadabraandroid.testdata.createAppContextMock
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

class CadabraAndroidImplInitializationKotlinTest : WordSpec({
    "CadabraAndroid" should {
        "throw exception if not initialized on getExperimentContext" {
            //TODO register the experiment here to make sure it throws exception b/c not initialized, not b/c it's
            //not found. Maybe change the other exception to not found

            shouldThrow<IllegalStateException> {
                CadabraAndroid.instance.getExperimentContext(SimpleAndroidVariants::class)
            }
        }

        "ignore double initialization" {
            CadabraAndroid.initialize(createAppContextMock())

            shouldNotThrow<Exception> {
                CadabraAndroid.initialize(createAppContextMock())
            }
        }
    }
})
