---
title: VariantResources.getLayoutId - cadabra-android
---

[cadabra-android](../../index.html) / [com.fo2rist.cadabra.android](../index.html) / [VariantResources](index.html) / [getLayoutId](./get-layout-id.html)

# getLayoutId

`abstract fun getLayoutId(defaultResourceId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Get layout res ID for variant.

### Parameters

`defaultResourceId` - default layout ID to be used as the base name and default value

### Exceptions

`android.content.res.Resources.NotFoundException` - if [defaultResourceId](get-layout-id.html#com.fo2rist.cadabra.android.VariantResources$getLayoutId(kotlin.Int)/defaultResourceId) doesn't exist.

**Return**
[defaultResourceId](get-layout-id.html#com.fo2rist.cadabra.android.VariantResources$getLayoutId(kotlin.Int)/defaultResourceId) when no variant-specific resource found.

