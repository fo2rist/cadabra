---
title: com.fo2rist.cadabra.android - cadabra-android
---

[cadabra-android](../index.html) / [com.fo2rist.cadabra.android](./index.html)

## Package com.fo2rist.cadabra.android

Main entry point for Cadabra if used for Android and classes for automatic resource resolving.

### Types

| [CadabraAndroid](-cadabra-android/index.html) | Android-specific cadabra extension that supports resources injection. Usage:`interface CadabraAndroid` |
| [ExperimentContext](-experiment-context/index.html) | Full context of experiment state as it's resolved for current session/user. If the experiment wasn't started the [variant](-experiment-context/variant.html) is null resources resolved as is.`class ExperimentContext : `[`VariantResources`](-variant-resources/index.html) |
| [VariantResources](-variant-resources/index.html) | Android resources accessor for particular experiment's Variant. Automatically resolves resources (strings, layouts etc.) associated with one of experiment's [Variant](#). Usage:`interface VariantResources` |

### Functions

| [configFromJson](config-from-json.html) | Create config from json. Format example:`fun configFromJson(json: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): <ERROR CLASS>` |

