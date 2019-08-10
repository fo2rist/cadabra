package com.fo2rist.cadabra.android

import com.fo2rist.cadabra.android.testdata.SimpleAndroidStaticResolver
import com.fo2rist.cadabra.android.testdata.SimpleAndroidExperiment
import com.fo2rist.cadabra.android.testdata.createAppContextMock
import io.kotlintest.Spec
import io.kotlintest.specs.WordSpec

class CadabraAndroidImplKotlinTest : WordSpec() {
    private val cadabraAndroidImpl = CadabraAndroidImpl()

    init {
        "CadabraAndroid getExperimentContext" should {
            "return resources object when called for Kotlin class" {
                cadabraAndroidImpl.getExperimentContext(SimpleAndroidExperiment::class)
            }

            "return resources object when called for Java class" {
                cadabraAndroidImpl.getExperimentContext(SimpleAndroidExperiment::class.java)
            }
        }
    }

    override fun beforeSpec(spec: Spec) {
        cadabraAndroidImpl.initialize(createAppContextMock())
        cadabraAndroidImpl.startExperiment(SimpleAndroidExperiment::class, SimpleAndroidStaticResolver())
    }
}
