---
title: VariantResources.getStringId - cadabra-android
---

[cadabra-android](../../index.html) / [com.fo2rist.cadabra.android](../index.html) / [VariantResources](index.html) / [getStringId](./get-string-id.html)

# getStringId

`abstract fun getStringId(defaultResourceId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Get string res ID for variant.

### Parameters

`defaultResourceId` - default string ID to be used as the base name and default value.

### Exceptions

`android.content.res.Resources.NotFoundException` - if [defaultResourceId](get-string-id.html#com.fo2rist.cadabra.android.VariantResources$getStringId(kotlin.Int)/defaultResourceId) doesn't exist.

**Return**
[defaultResourceId](get-string-id.html#com.fo2rist.cadabra.android.VariantResources$getStringId(kotlin.Int)/defaultResourceId) when no variant-specific resource found.

