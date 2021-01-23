---
title: ExperimentsConfigProvider - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [ExperimentsConfigProvider](./index.html)

# ExperimentsConfigProvider

`abstract class ExperimentsConfigProvider`

Provider of [ExperimentsConfig](../-experiments-config/index.html) that may configure experiments asynchronously.
Can be used if the experiment config not know in advance or takes a lot of time to load.
Extend this class and provide instance to [CadabraConfig.startExperimentsAsync](../-cadabra-config/start-experiments-async.html).
Once the config is ready call [provideConfig](provide-config.html).

### Constructors

| [&lt;init&gt;](-init-.html) | Provider of [ExperimentsConfig](../-experiments-config/index.html) that may configure experiments asynchronously. Can be used if the experiment config not know in advance or takes a lot of time to load. Extend this class and provide instance to [CadabraConfig.startExperimentsAsync](../-cadabra-config/start-experiments-async.html). Once the config is ready call [provideConfig](provide-config.html).`ExperimentsConfigProvider()` |

### Functions

| [onAttached](on-attached.html) | Override this function to get notified when provider is attached to Cadabra. By default does nothing. Note that Cadabra may call this method synchronously when [CadabraConfig.startExperimentsAsync](../-cadabra-config/start-experiments-async.html) is called.`open fun onAttached(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [provideConfig](provide-config.html) | Tell Cadabra that updated config is available. Can be called multiple times.`fun provideConfig(config: `[`ExperimentsConfig`](../-experiments-config/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

