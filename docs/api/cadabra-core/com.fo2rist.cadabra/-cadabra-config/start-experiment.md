---
title: CadabraConfig.startExperiment - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [CadabraConfig](index.html) / [startExperiment](./start-experiment.html)

# startExperiment

`abstract fun <V> startExperiment(experiment: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<V>, resolver: `[`Resolver`](../-resolver/index.html)`<V>): `[`CadabraConfig`](index.html)` where V : `[`Variant`](../-variant/index.html)`, V : `[`Enum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)`<V>`
`abstract fun <V> startExperiment(experiment: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<V>, resolver: `[`Resolver`](../-resolver/index.html)`<V>): `[`CadabraConfig`](index.html)` where V : `[`Variant`](../-variant/index.html)`, V : `[`Enum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)`<V>`

Register &amp; start experiment.
A combination of [registerExperiment](register-experiment.html) &amp; [startExperiments](start-experiments.html).

`abstract fun <V> startExperiment(experiment: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<V>): `[`CadabraConfig`](index.html)` where V : `[`Variant`](../-variant/index.html)`, V : `[`Enum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)`<V>`

Register &amp; start experiment with default variant active.
The first item of variants enum is the default one.
A combination of [registerExperiment](register-experiment.html) &amp; [startExperiments](start-experiments.html).

### Exceptions

`VariantNotFound` - if provided experiment doesn't have variants