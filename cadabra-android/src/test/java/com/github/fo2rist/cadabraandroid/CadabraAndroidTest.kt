package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabraandroid.test.R
import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidStaticResolver
import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidVariants
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals


@RunWith(RobolectricTestRunner::class)
class CadabraAndroidTest {
    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            CadabraAndroid.config.registerExperiment(SimpleAndroidVariants::class, SimpleAndroidStaticResolver())
        }
    }

    @Before
    fun setUp() {
        CadabraAndroidImpl.deinitialize()
    }

    @Test(expected = IllegalStateException::class)
    fun `getVariantResources should throw Exception if not initialized`() {
        CadabraAndroid.instance.getVariantResources(SimpleAndroidVariants.A)
    }

    @Test
    fun `getVariantResources should return resource if initialized`() {
        CadabraAndroid.initialize(RuntimeEnvironment.application)

        val resources = CadabraAndroid.instance.getVariantResources(SimpleAndroidVariants.A)
        assertEquals("test A", resources.getString(R.string.greeting_title_a))
    }
}
