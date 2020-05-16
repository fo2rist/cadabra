---
title: CadabraAndroid.getExperimentContext - cadabra-android
---

[cadabra-android](../../index.html) / [com.fo2rist.cadabra.android](../index.html) / [CadabraAndroid](index.html) / [getExperimentContext](./get-experiment-context.html)

# getExperimentContext

`abstract fun getExperimentContext(variantClass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<out <ERROR CLASS>>): `[`ExperimentContext`](../-experiment-context/index.html)

Get Android resources accessor for active experiment variant.
If the experiment is not started the context would behave as normal Android context.

### Exceptions

`ExperimentNotFound` - if experiment is not registered

`IllegalStateException` - if Cadabra was not initialized via [initialize](initialize.html).`abstract fun getExperimentContext(variantClass: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<out <ERROR CLASS>>): `[`ExperimentContext`](../-experiment-context/index.html)

Get Android resources accessor for active experiment variant.
If the experiment is not started the context would behave as normal Android context.

### Exceptions

`ExperimentNotFound` - if experiment is not registered

`NotInitializedException` - if Cadabra was not initialized via [initialize](initialize.html).