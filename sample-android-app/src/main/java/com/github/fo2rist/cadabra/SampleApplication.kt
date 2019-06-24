package com.github.fo2rist.cadabra

import android.app.Application
import com.github.fo2rist.cadabra.greetingexperiment.AutoResourceExperiment
import com.github.fo2rist.cadabra.greetingexperiment.PlainExperiment
import com.github.fo2rist.cadabra.resolvers.RandomResolver
import com.github.fo2rist.cadabraandroid.CadabraAndroid

/**
 * Sample app class.
 * Demonstrates how to register the experiment on the app creation.
 */
class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CadabraAndroid.initialize(this)
        CadabraAndroid.config
            // register experiment with properties provided via experiment variants class
            .startExperiment(
                PlainExperiment::class,
                RandomResolver(PlainExperiment::class)
            )
            // register experiment without explicitly provided properties
            .startExperiment(
                AutoResourceExperiment::class,
                RandomResolver(AutoResourceExperiment::class)
            )
    }
}
