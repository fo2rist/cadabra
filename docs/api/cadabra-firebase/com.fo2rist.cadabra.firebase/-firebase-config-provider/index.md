---
title: FirebaseConfigProvider - cadabra-firebase
---

[cadabra-firebase](../../index.html) / [com.fo2rist.cadabra.firebase](../index.html) / [FirebaseConfigProvider](./index.html)

# FirebaseConfigProvider

`class FirebaseConfigProvider`

[ExperimentsConfigProvider](#) that starts experiments automatically by Firebase Remote Config.
Supports two ways for providing config.

1. On the root level.
In that case all keys in Firebase that match names of registered experiments will be interpreted as experiments to be
activated and respective values as experiment Variants to activate.
E.g. For experiment `SomeTest` with variants `A`, `B` the remote config should have a key `SomeTest`
with value either `A` or `B`.

2. As nested Json.
In that case will looks for the specified key name in remote config and parse it as Json with a single object with
key:values pairs via [com.fo2rist.cadabra.android.configFromJson](#).

### Constructors

| [&lt;init&gt;](-init-.html) | Create Firebase Config Provider. Be default provider automatically fetches the default config and initiates the remote config loading. If the automatic fetching is turned off or the remote config was updated [startExperimentFromRemoteConfig](start-experiment-from-remote-config.html) triggers re-loading.`FirebaseConfigProvider(fetchAutomatically: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, useDefaults: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, rootElementKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null)` |

### Functions

| [onAttached](on-attached.html) | `fun onAttached(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startExperimentFromRemoteConfig](start-experiment-from-remote-config.html) | Apply current active remote config.`fun startExperimentFromRemoteConfig(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

