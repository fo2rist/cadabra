package com.github.fo2rist.cadabra

import android.app.Application
import com.github.fo2rist.cadabra.greetingexperiment.GreetingExperiment
import com.github.fo2rist.cadabra.greetingexperiment.GreetingVariants
import com.github.fo2rist.cadabra.sampleresovers.RandomResolver

/**
 * Sample app class.
 * Demonstrates how to register the experiment on the app creation.
 */
class CadabraTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Cadabra.config
            .registerExperiment(
                GreetingExperiment,
                RandomResolver(GreetingVariants::class)
            )
    }
}
