package com.fo2rist.cadabra.firebase.helpers

import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue

/**
 * Simple implementation of [FirebaseRemoteConfigValue] that can only contain string.
 */
class FakeFirebaseRemoteConfigValue(
    private val value: String?
) : FirebaseRemoteConfigValue {
    override fun asBoolean(): Boolean = throw IllegalArgumentException()

    override fun getSource(): Int = 0

    override fun asDouble(): Double = throw IllegalArgumentException()

    override fun asString(): String? = value

    override fun asByteArray(): ByteArray = throw IllegalArgumentException()

    override fun asLong(): Long = throw IllegalArgumentException()
}
