---
title: CadabraConfig.registerExperiment - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [CadabraConfig](index.html) / [registerExperiment](./register-experiment.html)

# registerExperiment

`abstract fun <V> registerExperiment(experiment: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<V>): `[`CadabraConfig`](index.html)` where V : `[`Variant`](../-variant/index.html)`, V : `[`Enum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)`<V>`
`abstract fun <V> registerExperiment(experiment: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<V>): `[`CadabraConfig`](index.html)` where V : `[`Variant`](../-variant/index.html)`, V : `[`Enum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)`<V>`

Register experiment.
Uses [experiment](register-experiment.html#com.fo2rist.cadabra.CadabraConfig$registerExperiment(kotlin.reflect.KClass((com.fo2rist.cadabra.CadabraConfig.registerExperiment.V)))/experiment) enum name as ID.
An experiment can only be used after it's registered.

