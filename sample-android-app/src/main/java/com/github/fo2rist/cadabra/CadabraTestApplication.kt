package com.github.fo2rist.cadabra

import android.app.Application
import com.github.fo2rist.cadabra.colorsexperimen.GreetingExperiment
import com.github.fo2rist.cadabra.colorsexperimen.GreetingVariants
import com.github.fo2rist.cadabra.colorsexperimen.RandomResolver

class CadabraTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Cadabra.Config
            .registerExperiment(
                GreetingExperiment,
                RandomResolver(GreetingVariants::class)
            )
    }
}
