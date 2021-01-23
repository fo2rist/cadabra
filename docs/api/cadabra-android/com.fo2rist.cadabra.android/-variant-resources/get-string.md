---
title: VariantResources.getString - cadabra-android
---

[cadabra-android](../../index.html) / [com.fo2rist.cadabra.android](../index.html) / [VariantResources](index.html) / [getString](./get-string.html)

# getString

`abstract fun getString(defaultResourceId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Get string for variant.

### Parameters

`defaultResourceId` - default res id to be used for name resolving.

### Exceptions

`android.content.res.Resources.NotFoundException` - if [defaultResourceId](get-string.html#com.fo2rist.cadabra.android.VariantResources$getString(kotlin.Int)/defaultResourceId) doesn't exist.

**Return**
string for [defaultResourceId](get-string.html#com.fo2rist.cadabra.android.VariantResources$getString(kotlin.Int)/defaultResourceId) when no variant-specific resource found.

