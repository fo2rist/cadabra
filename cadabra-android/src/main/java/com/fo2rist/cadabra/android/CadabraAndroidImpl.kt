package com.fo2rist.cadabra.android

import android.content.Context
import com.fo2rist.cadabra.Cadabra
import com.fo2rist.cadabra.CadabraConfig
import com.fo2rist.cadabra.Variant
import com.fo2rist.cadabra.android.exceptions.NotInitializedException
import kotlin.reflect.KClass

internal class CadabraAndroidImpl : CadabraAndroid, Cadabra by Cadabra.instance, CadabraConfig by Cadabra.config {

    private lateinit var appContext: Context

    fun initialize(context: Context) {
        if (::appContext.isInitialized) {
            return
        }

        appContext = context.applicationContext
    }

    override fun getExperimentContext(variantClass: KClass<out Variant>): ExperimentContext {
        return getExperimentContext(variantClass.java)
    }

    override fun getExperimentContext(variantClass: Class<out Variant>): ExperimentContext {
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

