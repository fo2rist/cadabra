---
title: ExperimentsConfigProvider.<init> - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [ExperimentsConfigProvider](index.html) / [&lt;init&gt;](./-init-.html)

# &lt;init&gt;

`ExperimentsConfigProvider()`

Provider of [ExperimentsConfig](../-experiments-config/index.html) that may configure experiments asynchronously.
Can be used if the experiment config not know in advance or takes a lot of time to load.
Extend this class and provide instance to [CadabraConfig.startExperimentsAsync](../-cadabra-config/start-experiments-async.html).
Once the config is ready call [provideConfig](provide-config.html).

