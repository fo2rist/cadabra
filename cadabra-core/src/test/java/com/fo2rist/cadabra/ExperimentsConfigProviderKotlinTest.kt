package com.fo2rist.cadabra

import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase

private val DUMMY_CONFIG = ExperimentsConfig.create()

private lateinit var experimentConfigProvider: ExperimentsConfigProvider
private var latestAppliedConfig: ExperimentsConfig? = null
private var wasAttached: Boolean = false

class ExperimentsConfigProviderKotlinTest : WordSpec({

    fun dummyApplyConfigCallback(config: ExperimentsConfig) {
        latestAppliedConfig = config
    }

    "provideConfig" should {

        "do nothing if not attached" {
            experimentConfigProvider.provideConfig(DUMMY_CONFIG)

            latestAppliedConfig shouldBe null
        }

        "call apply callback function after attached" {
            experimentConfigProvider.attach(::dummyApplyConfigCallback)

            experimentConfigProvider.provideConfig(DUMMY_CONFIG)

            latestAppliedConfig shouldBe DUMMY_CONFIG
        }

        "call onAttached after attached" {
            experimentConfigProvider.attach {}

            wasAttached shouldBe true
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        latestAppliedConfig = null
        experimentConfigProvider = object : ExperimentsConfigProvider() {
            override fun onAttached() {
                wasAttached = true
            }
        }
    }
}
