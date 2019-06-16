package com.github.fo2rist.cadabraandroid

import android.content.Context
import android.support.annotation.LayoutRes
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
 *  getStringId(R.string.title_a), getLayoutId(R.layout.layout_a) are called.
 */
interface VariantResources {
    /**
     * Get string res ID for variant.
     * @param defaultResourceId default string ID to be used as the base name and default value.
     * @return [defaultResourceId] when no variant-specific resource found.
     * @throws android.content.res.Resources.NotFoundException if [defaultResourceId] doesn't exist.
     */
    @StringRes
    fun getStringId(@StringRes defaultResourceId: Int): Int

    /**
     * Get string for variant.
     * @param defaultResourceId default res id to be used for name resolving.
     * @return string for [defaultResourceId] when no variant-specific resource found.
     * @throws android.content.res.Resources.NotFoundException if [defaultResourceId] doesn't exist.
     */
    fun getString(@StringRes defaultResourceId: Int): String

    /**
     * Get layout res ID for variant.
     * @param defaultResourceId default layout ID to be used as the base name and default value
     * @return [defaultResourceId] when no variant-specific resource found.
     * @throws android.content.res.Resources.NotFoundException if [defaultResourceId] doesn't exist.
     */
    @LayoutRes
    fun getLayoutId(@LayoutRes defaultResourceId: Int): Int
}

/**
 * Default [VariantResources] implementation for Android resources access.
 */
internal class VariantResourcesImpl(
    private val context: Context,
    private val variant: Variant,
    private val resourcesResolver: ResourcesResolver = defaultResourcesResolver
) : VariantResources {

    @StringRes
    override fun getStringId(@StringRes defaultResourceId: Int): Int =
        resourcesResolver.resolveStringResource(context, defaultResourceId, variant.name)

    override fun getString(@StringRes defaultResourceId: Int): String =
        context.resources.getString(getStringId(defaultResourceId))

    override fun getLayoutId(defaultResourceId: Int): Int =
        resourcesResolver.resolveLayoutResource(context, defaultResourceId, variant.name)

    companion object {
        private val defaultResourcesResolver = ResourcesResolver()
    }
}
