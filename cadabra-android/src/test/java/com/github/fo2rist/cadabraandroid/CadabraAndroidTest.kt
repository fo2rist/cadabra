package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabraandroid.test.R
import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidStaticResolver
import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidVariants
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals


/**
 * Integration test to make sure actual resources are used when the real context is present.
 * Robolectric serves as the provider of sort of real context good enough to access resources.
 * So far Robolectric test coverage is broken for JUnit5 so other it's not heavily used for unit-tests.
 */
@RunWith(RobolectricTestRunner::class)
class CadabraAndroidTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            CadabraAndroid.config.registerExperiment(
                SimpleAndroidVariants::class,
                SimpleAndroidStaticResolver(SimpleAndroidVariants.B)
            )
        }
    }

    @Test
    fun `getExperimentContext should return context with actual resources`() {
        CadabraAndroid.initialize(RuntimeEnvironment.application)

        val resources = CadabraAndroid.instance.getExperimentContext(SimpleAndroidVariants::class)
        assertEquals("test B", resources.getString(R.string.greeting_title_a))
    }
}
