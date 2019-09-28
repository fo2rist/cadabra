package com.fo2rist.cadabra.android

import java.util.Locale

/**
 * Generates names for resources by base resource name and given variant.
 */
internal class ResourceNamesGenerator {

    /**
     * Generate resource name to use by base name current variant.
     * E.g. For "string_a" and variant "A" should give "string_a", for "string_a" and variant "B" should give "string_b"
     */
    fun generateResourceName(baseResourceName: String, variantName: String?): String {
        return if (variantName == null)
            baseResourceName
        else
            baseResourceName.replaceAfterLast("_", variantName.toLowerCase(Locale.US))
    }
}
