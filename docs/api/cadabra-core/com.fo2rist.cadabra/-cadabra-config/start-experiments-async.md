---
title: CadabraConfig.startExperimentsAsync - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [CadabraConfig](index.html) / [startExperimentsAsync](./start-experiments-async.html)

# startExperimentsAsync

`abstract fun startExperimentsAsync(configProvider: `[`ExperimentsConfigProvider`](../-experiments-config-provider/index.html)`): `[`CadabraConfig`](index.html)

Start previously registered experiment asynchronously.
Registers the [ExperimentsConfigProvider](../-experiments-config-provider/index.html) that can update experiments config at any time.
When it provides the new config via [provideConfig](../-experiments-config-provider/provide-config.html) it works the same
way as [startExperiments](start-experiments.html).

