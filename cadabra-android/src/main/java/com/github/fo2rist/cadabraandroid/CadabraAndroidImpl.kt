package com.github.fo2rist.cadabraandroid

import android.content.Context
import android.support.annotation.StringRes
import com.github.fo2rist.cadabra.Cadabra
import com.github.fo2rist.cadabra.Variant

internal object CadabraAndroidImpl : CadabraAndroid, Cadabra by Cadabra.instance {

    private var appContext: Context? = null

    fun initialize(context: Context) {
        if (appContext != null) {
            return
        }

        appContext = context.applicationContext
    }

    /**
     * Unregister context.
     */
    internal fun deinitialize() {
        appContext = null
    }

    override fun getVariantResources(variant: Variant): VariantResources {
        checkNotNull(appContext) { "Must be initialized with android context to access resources" }.also { context ->
            return object : VariantResources {
                override val variant = variant

                @StringRes
                override fun getStringRes(@StringRes defaultOptionId: Int): Int {
                    val defaultResourceName = context.resources.getResourceEntryName(defaultOptionId)
                    val variantName = variant.id.toLowerCase()
                    val resourceName = defaultResourceName.replaceAfterLast("_", variantName)
                    return context.resources.getIdentifier(resourceName, "string", context.packageName)
                }

                override fun getString(@StringRes defaultOptionId: Int): String {
                    return context.resources.getString(getStringRes(defaultOptionId))
                }
            }
        }
    }
}
