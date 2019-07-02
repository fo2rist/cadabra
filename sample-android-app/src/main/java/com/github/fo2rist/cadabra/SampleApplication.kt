package com.github.fo2rist.cadabra

import android.app.Application
import com.github.fo2rist.cadabra.firebase.FirebaseConfigProvider
import com.github.fo2rist.cadabra.greetingexperiment.AutoResourceExperiment
import com.github.fo2rist.cadabra.greetingexperiment.FirebaseExperiment
import com.github.fo2rist.cadabra.greetingexperiment.PlainExperiment
import com.github.fo2rist.cadabra.resolvers.RandomResolver
import com.github.fo2rist.cadabraandroid.CadabraAndroid
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings



/**
 * Sample app class.
 * Demonstrates how to register the experiment on the app creation.
 */
class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeFirebaseRemoteConfig()

        CadabraAndroid.initialize(this)
        CadabraAndroid.config
            // start experiment with properties provided via experiment variants class
            .startExperiment(
                PlainExperiment::class,
                RandomResolver(PlainExperiment::class)
            )
            // start experiment without explicitly provided properties
            .startExperiment(
                AutoResourceExperiment::class,
                RandomResolver(AutoResourceExperiment::class)
            )
            // register experiment without starting
            .registerExperiment(FirebaseExperiment::class)
            // load experiments config from Firebase
            .startExperimentsAsync(FirebaseConfigProvider())
    }

    @Suppress("MagicNumber")
    private fun initializeFirebaseRemoteConfig() {
        // see https://firebase.google.com/docs/remote-config/use-config-android for details
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(60) // once a minute
            .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }
}
