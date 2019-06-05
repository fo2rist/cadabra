package com.github.fo2rist.cadabraandroid

import android.content.Context
import com.github.fo2rist.cadabra.Cadabra
import com.github.fo2rist.cadabra.CadabraConfig
import com.github.fo2rist.cadabra.Variant
import com.github.fo2rist.cadabraandroid.exceptions.NotInitializedException
import kotlin.reflect.KClass

internal class CadabraAndroidImpl : CadabraAndroid, Cadabra by Cadabra.instance, CadabraConfig by Cadabra.config {

    private lateinit var appContext: Context

    fun initialize(context: Context) {
        if (::appContext.isInitialized) {
            return
        }

        appContext = context.applicationContext
    }

    override fun getExperimentContext(variantClass: KClass<out Variant>): VariantResources {
        return getExperimentContext(variantClass.java)
    }

    override fun getExperimentContext(variantClass: Class<out Variant>): VariantResources {
        checkInitialized()

        val variant = getExperimentVariant(variantClass)
        return ExperimentContext(variant, VariantResourcesImpl(appContext, variant))
    }

    /**
     * @throws NotInitializedException if the value is null.
     */
    private fun checkInitialized() {
        if (!::appContext.isInitialized) {
            throw NotInitializedException()
        }
    }
}

