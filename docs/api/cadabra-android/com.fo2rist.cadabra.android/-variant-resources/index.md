---
title: VariantResources - cadabra-android
---

[cadabra-android](../../index.html) / [com.fo2rist.cadabra.android](../index.html) / [VariantResources](./index.html)

# VariantResources

`interface VariantResources`

Android resources accessor for particular experiment's Variant.
Automatically resolves resources (strings, layouts etc.) associated with one of experiment's [Variant](#).
Usage:

* create resources named "name_&lt;variant-name&gt;" as usually
* cadabra will strip &lt;variant-name&gt; and generate names to look up according to other variant names

E.g. For experiment variants A, B declare strings with names "title_a", "title_b"
or layouts "layout_a", "layout_b" etc.

Then if the experiment is resolved to variant B, cadabra will return "title_b" and "layout_b" when
getStringId(R.string.title_a), getLayoutId(R.layout.layout_a) are called.

If the experiment wasn't started resolves all resources into defaults.

### Functions

| [getLayoutId](get-layout-id.html) | Get layout res ID for variant.`abstract fun getLayoutId(defaultResourceId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getString](get-string.html) | Get string for variant.`abstract fun getString(defaultResourceId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStringId](get-string-id.html) | Get string res ID for variant.`abstract fun getStringId(defaultResourceId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Inheritors

| [ExperimentContext](../-experiment-context/index.html) | Full context of experiment state as it's resolved for current session/user. If the experiment wasn't started the [variant](../-experiment-context/variant.html) is null resources resolved as is.`class ExperimentContext : `[`VariantResources`](./index.html) |

