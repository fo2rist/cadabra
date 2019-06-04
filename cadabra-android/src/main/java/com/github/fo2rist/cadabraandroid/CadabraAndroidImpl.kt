package com.github.fo2rist.cadabraandroid

import android.content.Context
import com.github.fo2rist.cadabra.Cadabra
import com.github.fo2rist.cadabra.CadabraConfig
import com.github.fo2rist.cadabra.Variant
import kotlin.reflect.KClass

internal class CadabraAndroidImpl : CadabraAndroid, Cadabra by Cadabra.instance, CadabraConfig by Cadabra.config {

    private var appContext: Context? = null

    fun initialize(context: Context) {
        if (appContext != null) {
            return
        }

        appContext = context.applicationContext
    }

    override fun getExperimentContext(variantClass: KClass<out Variant>): VariantResources {
        return getExperimentContext(variantClass.java)
    }

    override fun getExperimentContext(variantClass: Class<out Variant>): VariantResources {
        checkNotNull(appContext) { "Must be initialized with android context to access resources" }.let { context ->
            val variant = getExperimentVariant(variantClass)
            return ExperimentContext(variant, VariantResourcesImpl(context, variant))
        }
    }
}
