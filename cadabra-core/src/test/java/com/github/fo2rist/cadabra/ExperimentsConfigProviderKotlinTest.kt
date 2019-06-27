package com.github.fo2rist.cadabra

import io.kotlintest.TestCase
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

private val DUMMY_CONFIG = ExperimentsConfig.create()

private lateinit var experimentConfigProvider: ExperimentsConfigProvider
private var latestActivatedConfig: ExperimentsConfig? = null
private var wasAttached: Boolean = false

class ExperimentsConfigProviderKotlinTest : WordSpec({

    fun registerConfig(config: ExperimentsConfig) {
        latestActivatedConfig = config
    }

    "provideConfig" should {

        "do nothing if not attached" {
            experimentConfigProvider.provideConfig(DUMMY_CONFIG)

            latestActivatedConfig shouldBe null
        }

        "call activation function after attached" {
            experimentConfigProvider.attach(::registerConfig)

            experimentConfigProvider.provideConfig(DUMMY_CONFIG)

            latestActivatedConfig shouldBe DUMMY_CONFIG
        }

        "call onAttached after attached" {
            experimentConfigProvider.attach {}

            wasAttached shouldBe true
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        latestActivatedConfig = null
        experimentConfigProvider = object : ExperimentsConfigProvider() {
            override fun onAttached() {
                wasAttached = true
            }
        }
    }
}
