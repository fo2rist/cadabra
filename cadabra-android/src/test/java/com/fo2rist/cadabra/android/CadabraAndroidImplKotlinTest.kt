package com.fo2rist.cadabra.android

import com.fo2rist.cadabra.Variant
import com.fo2rist.cadabra.android.testdata.SimpleAndroidExperiment
import com.fo2rist.cadabra.android.testdata.SimpleAndroidStaticResolver
import com.fo2rist.cadabra.android.testdata.createAppContextMock
import com.fo2rist.cadabra.exceptions.ExperimentNotFound
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldNotBe

private enum class NotRegisteredExperiment : Variant

class CadabraAndroidImplKotlinTest : WordSpec() {
    private val cadabraAndroidImpl = CadabraAndroidImpl()

    init {
        "CadabraAndroid getExperimentContext" should {
            "return resources object when called for Kotlin class" {
                cadabraAndroidImpl.getExperimentContext(SimpleAndroidExperiment::class) shouldNotBe null
            }

            "return resources object when called for Java class" {
                cadabraAndroidImpl.getExperimentContext(SimpleAndroidExperiment::class.java) shouldNotBe null
            }

            "throw ExperimentNotFound when experiment not registered" {
                shouldThrow<ExperimentNotFound> {
                    cadabraAndroidImpl.getExperimentContext(NotRegisteredExperiment::class)
                }
            }
        }
    }

    override fun beforeSpec(spec: Spec) {
        cadabraAndroidImpl.initialize(createAppContextMock())
        cadabraAndroidImpl.startExperiment(SimpleAndroidExperiment::class, SimpleAndroidStaticResolver())
    }
}
