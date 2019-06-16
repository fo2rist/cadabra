package com.github.fo2rist.cadabra

import android.app.Application
import com.github.fo2rist.cadabra.greetingexperiment.AutoResourceVariants
import com.github.fo2rist.cadabra.greetingexperiment.PlainExperiment
import com.github.fo2rist.cadabra.greetingexperiment.PlainExperiment.PlainVariants
import com.github.fo2rist.cadabra.sampleresovers.RandomResolver
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
            // register experiment via explicit Experiment object that contains variants information
            .registerExperiment(
                PlainExperiment.INSTANCE,
                RandomResolver(PlainVariants::class)
            )
            // register experiment via providing variants class directly
            .registerExperiment(
                AutoResourceVariants::class,
                RandomResolver(AutoResourceVariants::class)
            )
    }
}
