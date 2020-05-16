---
title: Cadabra - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [Cadabra](./index.html)

# Cadabra

`interface Cadabra`

Cadabra A/B experiments.

To use:

* create an experiment enum implementing [Variant](../-variant/index.html) interface
* create [Resolver](../-resolver/index.html) or use one of the predefined to define which variant ot use for particular user/session.
* register and start experiments via [Cadabra.config](config.html)
* when it's time to apply experimental parameters get the variant via [Cadabra.instance](instance.html)'s [getExperimentVariant](get-experiment-variant.html)

Experiment registration &amp; start:
When experiment is registered via [CadabraConfig.registerExperiment](../-cadabra-config/register-experiment.html) it become discoverable by name and waiting
to be started. For such experiment [getExperimentVariant](get-experiment-variant.html) can not be used yet.
To start registered experiments use [CadabraConfig.startExperiments](../-cadabra-config/start-experiments.html). You can register multiple experiments and then
start as many as needed with a single [CadabraConfig.startExperiments](../-cadabra-config/start-experiments.html) call.
Or use [CadabraConfig.startExperiment](../-cadabra-config/start-experiment.html) to register and start experiment at the same time.

### Functions

| [getExperimentVariant](get-experiment-variant.html) | Get experiment variant to apply for this user/session by [Variant](../-variant/index.html) class. Only works if the experiment is started.`abstract fun <V : `[`Variant`](../-variant/index.html)`> getExperimentVariant(experiment: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<V>): V?`<br>`abstract fun <V : `[`Variant`](../-variant/index.html)`> getExperimentVariant(experiment: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<V>): V?` |

### Companion Object Properties

| [config](config.html) | Entry point for Cadabra configuration. Use it at the application startup to keep all experiments configuration in one place.`val config: `[`CadabraConfig`](../-cadabra-config/index.html) |
| [instance](instance.html) | Entry point Cadabra experiment variants usage.`val instance: `[`Cadabra`](./index.html) |

