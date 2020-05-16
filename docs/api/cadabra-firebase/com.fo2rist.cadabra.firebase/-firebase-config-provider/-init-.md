---
title: FirebaseConfigProvider.<init> - cadabra-firebase
---

[cadabra-firebase](../../index.html) / [com.fo2rist.cadabra.firebase](../index.html) / [FirebaseConfigProvider](index.html) / [&lt;init&gt;](./-init-.html)

# &lt;init&gt;

`FirebaseConfigProvider(fetchAutomatically: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, useDefaults: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, rootElementKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null)`

Create Firebase Config Provider.
Be default provider automatically fetches the default config and initiates the remote config loading.
If the automatic fetching is turned off or the remote config was updated [startExperimentFromRemoteConfig](start-experiment-from-remote-config.html)
triggers re-loading.

### Parameters

`fetchAutomatically` - automatically fetch and activate latest Firebase Config once attached to Cadabra

`useDefaults` - automatically load default Firebase Config values once attached to Cadabra

`rootElementKey` - name of the root element in Firebase Remote Config.
If present the element with that key will be fetched from Remote config a single configuration JSON,
otherwise will scan all keys in Remote config and match them with registered experiment names.