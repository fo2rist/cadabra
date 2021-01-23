---
title: Cadabra.getExperimentVariant - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [Cadabra](index.html) / [getExperimentVariant](./get-experiment-variant.html)

# getExperimentVariant

`abstract fun <V : `[`Variant`](../-variant/index.html)`> getExperimentVariant(experiment: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<V>): V?`
`abstract fun <V : `[`Variant`](../-variant/index.html)`> getExperimentVariant(experiment: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<V>): V?`

Get experiment variant to apply for this user/session by [Variant](../-variant/index.html) class.
Only works if the experiment is started.

### Exceptions

`ExperimentNotFound` - if experiment is not registered

**See Also**

[CadabraConfig.registerExperiment](../-cadabra-config/register-experiment.html)

[CadabraConfig.startExperiment](../-cadabra-config/start-experiment.html)

**Return**
null if the experiment was not started

