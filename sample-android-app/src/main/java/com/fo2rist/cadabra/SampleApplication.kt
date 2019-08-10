package com.fo2rist.cadabra

import android.app.Application
import com.fo2rist.cadabra.firebase.FirebaseConfigProvider
import com.fo2rist.cadabra.greetingexperiment.AutoResourceExperiment
import com.fo2rist.cadabra.greetingexperiment.FirebaseJsonExperiment
import com.fo2rist.cadabra.greetingexperiment.FirebaseKeyValueExperiment
import com.fo2rist.cadabra.greetingexperiment.PlainExperiment
import com.fo2rist.cadabra.resolvers.RandomResolver
import com.fo2rist.cadabra.android.CadabraAndroid
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
            // register experiments without starting
            .registerExperiment(FirebaseJsonExperiment::class)
            .registerExperiment(FirebaseKeyValueExperiment::class)
            // load experiments config from Firebase with two ways of loading
            .startExperimentsAsync(FirebaseConfigProvider())                                       // as key:values
            .startExperimentsAsync(FirebaseConfigProvider(rootElementKey = "cadabra_experiments")) // and as Json
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
