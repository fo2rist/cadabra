package com.github.fo2rist.cadabraandroid

import android.content.res.Resources
import com.github.fo2rist.cadabraandroid.test.R
import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidExperiment
import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidStaticResolver
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals


/**
 * Integration test to make sure actual resources are used when the real context is present.
 * Robolectric serves as the provider of sorta real context, good enough to access resources.
 */
@RunWith(RobolectricTestRunner::class)
class CadabraAndroidTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            CadabraAndroid.config.startExperiment(
                SimpleAndroidExperiment::class,
                SimpleAndroidStaticResolver(SimpleAndroidExperiment.B)
            )
        }
    }

    @get:Rule
    var exceptionRule: ExpectedException = ExpectedException.none()

    private val cadabraAndroid: CadabraAndroid by lazy {
        CadabraAndroid.instance.also { CadabraAndroid.initialize(RuntimeEnvironment.application) }
    }

    @Test
    fun `getExperimentContext should return context with actual resources`() {
        val experimentContext = cadabraAndroid.getExperimentContext(SimpleAndroidExperiment::class)
        assertEquals(SimpleAndroidExperiment.B, experimentContext.variant)
        assertEquals(R.string.test_b, experimentContext.getStringId(R.string.test_a))
        assertEquals("test B", experimentContext.getString(R.string.test_a))
        assertEquals(R.layout.test_b, experimentContext.getLayoutId(R.layout.test_a))
    }

    @Test
    fun `getXyz throws exception when default resource is missing`() {
        exceptionRule.expect(Resources.NotFoundException::class.java)
        cadabraAndroid.getExperimentContext(SimpleAndroidExperiment::class).getStringId(-1)

        exceptionRule.expect(Resources.NotFoundException::class.java)
        cadabraAndroid.getExperimentContext(SimpleAndroidExperiment::class).getString(-1)

        exceptionRule.expect(Resources.NotFoundException::class.java)
        cadabraAndroid.getExperimentContext(SimpleAndroidExperiment::class).getLayoutId(-1)
    }
}
