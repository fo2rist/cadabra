package com.fo2rist.cadabra.android

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes

/**
 * Resolved resources by base resource ID and given variant.
 */
internal class ResourcesResolver(
    private val namesGenerator: ResourceNamesGenerator = defaultResourceNamesGenerator
) {

    /**
     * Resolve string by given default string and experiment variant to apply.
     * @param variantName [com.fo2rist.cadabra.Variant.name] of active variant or null
     * @return string for given variant or [defaultResourceId] if variant-specific option not found.
     * @throws android.content.res.Resources.NotFoundException if default resource doesn't exist.
     */
    @StringRes
    fun resolveStringResource(context: Context, @StringRes defaultResourceId: Int, variantName: String?): Int {
        val defaultResourceName = context.stringIdToName(defaultResourceId)
        val resourceName = namesGenerator.generateResourceName(defaultResourceName, variantName)
        return context.stringNameToId(resourceName)
            .orNonZeroDefault(defaultResourceId)
    }

    /**
     * Resolve layout by given default layout and experiment variant to apply.
     * @param variantName [com.fo2rist.cadabra.Variant.name] of active variant or null
     * @return string for given variant or [defaultResourceId] if variant-specific option not found.
     * @throws android.content.res.Resources.NotFoundException if default resource doesn't exist.
     */
    @LayoutRes
    fun resolveLayoutResource(context: Context, @LayoutRes defaultResourceId: Int, variantName: String?): Int {
        val defaultResourceName = context.layoutIdToName(defaultResourceId)
        val resourceName = namesGenerator.generateResourceName(defaultResourceName, variantName)
        return context.layoutNameToId(resourceName)
            .orNonZeroDefault(defaultResourceId)
    }

    companion object {
        private val defaultResourceNamesGenerator = ResourceNamesGenerator()
    }
}

/**
 * Wrapper for [android.content.res.Resources.getResourceEntryName]
 * @throws android.content.res.Resources.NotFoundException if given ID doesn't exist.
 */
private fun Context.stringIdToName(@StringRes resId: Int): String =
    resources.getResourceEntryName(resId)

/**
 * Wrapper for [android.content.res.Resources.getIdentifier].
 * @return 0 if resource not found.
 */
@StringRes
private fun Context.stringNameToId(resourceName: String) =
    resources.getIdentifier(resourceName, "string", packageName)

/**
 * Wrapper for [android.content.res.Resources.getResourceEntryName]
 * @throws android.content.res.Resources.NotFoundException if given ID doesn't exist.
 */
private fun Context.layoutIdToName(@LayoutRes resId: Int): String =
    resources.getResourceEntryName(resId)

/**
 * Wrapper for [android.content.res.Resources.getIdentifier].
 * @return 0 if resource not found.
 */
@LayoutRes
private fun Context.layoutNameToId(resourceName: String): Int =
    resources.getIdentifier(resourceName, "layout", packageName)

/**
 * @return value itself it's non-zero or provided [default].
 */
private fun Int.orNonZeroDefault(default: Int): Int {
    return if (this != 0) this else default
}
