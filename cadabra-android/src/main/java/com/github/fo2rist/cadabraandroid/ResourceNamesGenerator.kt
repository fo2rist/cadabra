package com.github.fo2rist.cadabraandroid

/**
 * Generates names for resources by base resource name and given variant.
 */
internal class ResourceNamesGenerator {

    /**
     * Generate resource name to use by base name current variant.
     * E.g. For "string_a" and variant "A" should give "string_a", for "string_a" and variant "B" should give "string_b"
     */
    fun generateResourceName(baseResourceName: String, variantName: String): String {
        return baseResourceName.replaceAfterLast("_", variantName.toLowerCase())
    }
}
