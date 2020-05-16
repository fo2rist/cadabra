---
title: ExperimentsConfigProvider.onAttached - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [ExperimentsConfigProvider](index.html) / [onAttached](./on-attached.html)

# onAttached

`open fun onAttached(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Override this function to get notified when provider is attached to Cadabra.
By default does nothing.
Note that Cadabra may call this method synchronously when [CadabraConfig.startExperimentsAsync](../-cadabra-config/start-experiments-async.html) is called.

