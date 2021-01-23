---
title: CadabraConfig - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [CadabraConfig](./index.html)

# CadabraConfig

`interface CadabraConfig`

Cadabra's configuration.

### Functions

| [registerExperiment](register-experiment.html) | Register experiment. Uses [experiment](register-experiment.html#com.fo2rist.cadabra.CadabraConfig$registerExperiment(kotlin.reflect.KClass((com.fo2rist.cadabra.CadabraConfig.registerExperiment.V)))/experiment) enum name as ID. An experiment can only be used after it's registered.`abstract fun <V> registerExperiment(experiment: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<V>): `[`CadabraConfig`](./index.html)` where V : `[`Variant`](../-variant/index.html)`, V : `[`Enum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)`<V>`<br>`abstract fun <V> registerExperiment(experiment: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<V>): `[`CadabraConfig`](./index.html)` where V : `[`Variant`](../-variant/index.html)`, V : `[`Enum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)`<V>` |
| [startExperiment](start-experiment.html) | Register &amp; start experiment. A combination of [registerExperiment](register-experiment.html) &amp; [startExperiments](start-experiments.html).`abstract fun <V> startExperiment(experiment: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<V>, resolver: `[`Resolver`](../-resolver/index.html)`<V>): `[`CadabraConfig`](./index.html)` where V : `[`Variant`](../-variant/index.html)`, V : `[`Enum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)`<V>`<br>`abstract fun <V> startExperiment(experiment: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<V>, resolver: `[`Resolver`](../-resolver/index.html)`<V>): `[`CadabraConfig`](./index.html)` where V : `[`Variant`](../-variant/index.html)`, V : `[`Enum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)`<V>`<br>Register &amp; start experiment with default variant active. The first item of variants enum is the default one. A combination of [registerExperiment](register-experiment.html) &amp; [startExperiments](start-experiments.html).`abstract fun <V> startExperiment(experiment: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<V>): `[`CadabraConfig`](./index.html)` where V : `[`Variant`](../-variant/index.html)`, V : `[`Enum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)`<V>` |
| [startExperiments](start-experiments.html) | Start previously registered experiments. Experiments previously started will be reconfigured with a new active variant specified. Experiments with unknown IDs will be ignored.`abstract fun startExperiments(config: `[`ExperimentsConfig`](../-experiments-config/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startExperimentsAsync](start-experiments-async.html) | Start previously registered experiment asynchronously. Registers the [ExperimentsConfigProvider](../-experiments-config-provider/index.html) that can update experiments config at any time. When it provides the new config via [provideConfig](../-experiments-config-provider/provide-config.html) it works the same way as [startExperiments](start-experiments.html).`abstract fun startExperimentsAsync(configProvider: `[`ExperimentsConfigProvider`](../-experiments-config-provider/index.html)`): `[`CadabraConfig`](./index.html) |

