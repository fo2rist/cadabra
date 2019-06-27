package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabraandroid.testdata.EMPTY_EXPERIMENT_CONFIG
import com.github.fo2rist.cadabraandroid.testdata.INCORRECT_EXPERIMENT_CONFIG
import com.github.fo2rist.cadabraandroid.testdata.SINGLE_EXPERIMENT_CONFIG
import com.github.fo2rist.cadabraandroid.testdata.TWO_EXPERIMENTS_CONFIG
import org.json.JSONException
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExperimentConfigExtensionsTest {

    @Test(expected = JSONException::class)
    fun `configFromJson throws exception for incorrect Json`() {
        configFromJson(INCORRECT_EXPERIMENT_CONFIG)
    }

    @Test
    fun `configFromJson is empty for empty string`() {
        val experimentsConfig = configFromJson("")

        assertEquals(0, experimentsConfig.entries.size)
    }

    @Test
    fun `configFromJson is empty for empty Json`() {
        val experimentsConfig = configFromJson(EMPTY_EXPERIMENT_CONFIG)

        assertEquals(0, experimentsConfig.entries.size)
    }

    @Test
    fun `configFromJson can parse single entry`() {
        val experimentsConfig = configFromJson(SINGLE_EXPERIMENT_CONFIG)

        assertEquals(1, experimentsConfig.entries.size)
    }

    @Test
    fun `configFromJson can parse multiple entries`() {
        val experimentsConfig = configFromJson(TWO_EXPERIMENTS_CONFIG)

        assertEquals(2, experimentsConfig.entries.size)
    }
}
