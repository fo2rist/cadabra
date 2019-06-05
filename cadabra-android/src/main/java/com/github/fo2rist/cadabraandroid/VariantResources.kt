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
 *  getStringResId(R.string.title_a), getLayout(R.layout.layout_a) are called.
 */
interface VariantResources {
    /**
     * Get string res ID.
     * @param defaultResourceId default res id to be used for name resolving.
     * @return [defaultResourceId] when no variant-specific resource found.
     */
    @StringRes
    fun getStringResId(@StringRes defaultResourceId: Int): Int

    /**
     * Get string.
     * @param defaultResourceId default res id to be used for name resolving.
     * @return string for [defaultResourceId] when no variant-specific resource found.
     */
    fun getString(@StringRes defaultResourceId: Int): String
}

/**
 * Default [VariantResources] implementation for Android resources access.
 */
internal class VariantResourcesImpl(
    private val context: Context,
    private val variant: Variant,
    private val resourcesResolver: ResourcesResolver = defaultResourcesResolver
) : VariantResources {

    /**
     * @throws android.content.res.Resources.NotFoundException if given ID doesn't exist.
     */
    @StringRes
    override fun getStringResId(@StringRes defaultResourceId: Int): Int {
        return resourcesResolver.resolveStringResource(context, defaultResourceId, variant.id)
    }

    /**
     * @throws android.content.res.Resources.NotFoundException if given ID doesn't exist.
     */
    override fun getString(@StringRes defaultResourceId: Int): String {
        return context.getString(getStringResId(defaultResourceId))
    }

    companion object {
        val defaultResourcesResolver = ResourcesResolver()
    }
}
