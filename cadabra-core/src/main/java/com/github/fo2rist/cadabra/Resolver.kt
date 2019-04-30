package com.github.fo2rist.cadabra

/**
 * Resolves the experiment by giving single Variant among available.
 */
interface Resolver<V> where V : Variant, V : Enum<V> {
    /**
     * Get variant to be used now.
     * Note that Cadabra calls this method every time variant resolution is required, so if the same variant should be
     * provided for particular user/session/etc. make sure either cache it in the app and don't ask twice or
     * implement receiver in a way it takes care of that. Receiver-side implementation is recommended to keep the
     * app code free from the experiment-related code.
     */
    val variant: V
}
