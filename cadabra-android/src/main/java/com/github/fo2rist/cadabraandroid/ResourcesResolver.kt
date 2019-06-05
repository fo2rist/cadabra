package com.github.fo2rist.cadabraandroid

import android.content.Context
import android.support.annotation.StringRes

/**
 * Resolved resources by base resource ID and given variant.
 */
internal class ResourcesResolver(
    private val namesGenerator: ResourceNamesGenerator = defaultResourceNamesGenerator
) {

    @StringRes
    fun resolveStringResource(context: Context, @StringRes defaultResourceId: Int, variantName: String): Int {
        val defaultResourceName = context.stringIdToName(defaultResourceId)
        val resourceName = namesGenerator.generateResourceName(defaultResourceName, variantName)
        return context.stringNameToId(resourceName)
    }

    companion object {
        val defaultResourceNamesGenerator = ResourceNamesGenerator()
    }
}

/**
 * Wrapper for [android.content.res.Resources.getResourceEntryName]
 * @throws android.content.res.Resources.NotFoundException if given ID doesn't exist.
 */
fun Context.stringIdToName(@StringRes resId: Int): String =
    resources.getResourceEntryName(resId)

/**
 * Wrapper for [android.content.res.Resources.getIdentifier].
 * @return 0 if resource not found.
 */
@StringRes
fun Context.stringNameToId(resourceName: String) =
    resources.getIdentifier(resourceName, "string", packageName)
