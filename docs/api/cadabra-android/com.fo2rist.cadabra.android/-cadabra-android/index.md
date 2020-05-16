---
title: CadabraAndroid - cadabra-android
---

[cadabra-android](../../index.html) / [com.fo2rist.cadabra.android](../index.html) / [CadabraAndroid](./index.html)

# CadabraAndroid

`interface CadabraAndroid`

Android-specific cadabra extension that supports resources injection.
Usage:

* register/start experiments with [Cadabra.config](#)
* initialize context with [initialize](initialize.html)
* access resources associated with experiment via [getExperimentContext](get-experiment-context.html), see [VariantResources](../-variant-resources/index.html) for details

**See Also**

[Cadabra](#)

### Functions

| [getExperimentContext](get-experiment-context.html) | Get Android resources accessor for active experiment variant. If the experiment is not started the context would behave as normal Android context.`abstract fun getExperimentContext(variantClass: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<out <ERROR CLASS>>): `[`ExperimentContext`](../-experiment-context/index.html)<br>`abstract fun getExperimentContext(variantClass: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<out <ERROR CLASS>>): `[`ExperimentContext`](../-experiment-context/index.html) |

### Companion Object Properties

| [config](config.html) | Entry point for Cadabra configuration. Same as [Cadabra.config](#).`val config: <ERROR CLASS>` |
| [instance](instance.html) | Entry point CadabraAndroid experiment variants usage.`val instance: `[`CadabraAndroid`](./index.html) |

### Companion Object Functions

| [initialize](initialize.html) | Initialize Cadabra for resources access. Initialization is required prior resources usage but not required to register and access experiments. [Context.getApplicationContext](#) will be used by Cadabra as the context.`fun initialize(context: <ERROR CLASS>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

