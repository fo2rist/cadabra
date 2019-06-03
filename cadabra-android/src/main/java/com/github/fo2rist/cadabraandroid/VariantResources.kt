package com.github.fo2rist.cadabraandroid

import android.content.Context
import android.support.annotation.StringRes
import com.github.fo2rist.cadabra.Variant

/**
 * Android resources accessor for particular experiment Variant.
 * Automatically resolves resources (strings, layouts etc.) associated with one of experiment's [Variant].
 * Usage:
 *  - create resources named "name_<variant-name>" as usually
 *  - cadabra will strip <variant-name> and generate names to look up according to other variant names
 *
 *  E.g. For experiment variants A, B declare strings with names "title_a", "title_b"
 *  or layouts "layout_a", "layout_b" etc.
 *
 *  Then if the experiment is resolved to variant B, cadabra will return "title_b" and "layout_b" when
 *  getStringRes(R.string.title_a), getLayout(R.layout.layout_a) are called.
 */
interface VariantResources {
    /**
     * Get string res ID.
     * @param defaultOptionId default res id to be used for name resolving.
     * @return [defaultOptionId] when no variant-specific resource found.
     */
    @StringRes
    fun getStringRes(@StringRes defaultOptionId: Int): Int

    /**
     * Get string.
     * @param defaultOptionId default res id to be used for name resolving.
     * @return string for [defaultOptionId] when no variant-specific resource found.
     */
    fun getString(@StringRes defaultOptionId: Int): String
}

/**
 * Default [VariantResources] implementation for Android resources access.
 */
internal class VariantResourcesImpl(
    private val context: Context,
    private val variant: Variant
) : VariantResources {

    @StringRes
    override fun getStringRes(@StringRes defaultOptionId: Int): Int {
        val defaultResourceName = context.resources.getResourceEntryName(defaultOptionId)
        val variantName = variant.id.toLowerCase()
        val resourceName = defaultResourceName.replaceAfterLast("_", variantName)

        return context.resources.getIdentifier(resourceName, "string", context.packageName)
    }

    override fun getString(@StringRes defaultOptionId: Int): String {
        val stringResId = getStringRes(defaultOptionId)
        return context.resources.getString(stringResId)
    }
}
