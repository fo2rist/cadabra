---
title: CadabraConfig.startExperiments - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [CadabraConfig](index.html) / [startExperiments](./start-experiments.html)

# startExperiments

`abstract fun startExperiments(config: `[`ExperimentsConfig`](../-experiments-config/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Start previously registered experiments.
Experiments previously started will be reconfigured with a new active variant specified.
Experiments with unknown IDs will be ignored.

### Exceptions

`UnknownVariant` - if provided variant doesn't match registered experiment