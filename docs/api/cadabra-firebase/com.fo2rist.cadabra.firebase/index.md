---
title: com.fo2rist.cadabra.firebase - cadabra-firebase
---

[cadabra-firebase](../index.html) / [com.fo2rist.cadabra.firebase](./index.html)

## Package com.fo2rist.cadabra.firebase

Contain Resolver implementation that can parse data from Firebase Remote Config.

### Types

| [FirebaseConfigProvider](-firebase-config-provider/index.html) | [ExperimentsConfigProvider](#) that starts experiments automatically by Firebase Remote Config. Supports two ways for providing config.`class FirebaseConfigProvider` |

### Functions

| [configFromFirebaseConfig](config-from-firebase-config.html) | Create config from &lt;experiment_id: variant_name&gt; map. Interprets each [FirebaseRemoteConfigValue](#) as a strings ignoring nulls and empty values.`fun configFromFirebaseConfig(map: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, <ERROR CLASS>?>): <ERROR CLASS>` |

