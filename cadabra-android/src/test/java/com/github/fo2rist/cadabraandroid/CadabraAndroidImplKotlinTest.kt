package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidStaticResolver
import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidVariants
import com.github.fo2rist.cadabraandroid.testdata.createAppContextMock
import io.kotlintest.Spec
import io.kotlintest.specs.WordSpec

class CadabraAndroidImplKotlinTest : WordSpec({

    "CadabraAndroid getExperimentContext" should {
        "return resources object when called for Kotlin class" {
            CadabraAndroid.instance.getExperimentContext(SimpleAndroidVariants::class)
        }

        "return resources object when called for Java class" {
            CadabraAndroid.instance.getExperimentContext(SimpleAndroidVariants::class.java)
        }
    }
}) {
    override fun beforeSpec(spec: Spec) {
        CadabraAndroid.initialize(createAppContextMock())
        CadabraAndroid.config.registerExperiment(SimpleAndroidVariants::class, SimpleAndroidStaticResolver())
    }
}
