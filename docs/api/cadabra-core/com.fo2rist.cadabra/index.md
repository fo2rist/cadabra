---
title: com.fo2rist.cadabra - cadabra-core
---

[cadabra-core](../index.html) / [com.fo2rist.cadabra](./index.html)

## Package com.fo2rist.cadabra

### Types

| [Cadabra](-cadabra/index.html) | Cadabra A/B experiments.`interface Cadabra` |
| [CadabraConfig](-cadabra-config/index.html) | Cadabra's configuration.`interface CadabraConfig` |
| [ExperimentsConfig](-experiments-config/index.html) | Configuration for enabled experiments. A map  Active Variant&gt; Name.`class ExperimentsConfig` |
| [ExperimentsConfigProvider](-experiments-config-provider/index.html) | Provider of [ExperimentsConfig](-experiments-config/index.html) that may configure experiments asynchronously. Can be used if the experiment config not know in advance or takes a lot of time to load. Extend this class and provide instance to [CadabraConfig.startExperimentsAsync](-cadabra-config/start-experiments-async.html). Once the config is ready call [provideConfig](-experiments-config-provider/provide-config.html).`abstract class ExperimentsConfigProvider` |
| [Resolver](-resolver/index.html) | Resolves the experiment by giving single Variant among available.`interface Resolver<V : `[`Variant`](-variant/index.html)`>` |
| [Variant](-variant/index.html) | A single option (variant) of the particular experiment. Implement this interface by the enum so that each item represents the experiment variant, and provide experimental parameters via properties/functions of that enum.`interface Variant` |

### Extensions for External Classes

| [java.lang.Class](java.lang.-class/index.html) |  |
| [kotlin.reflect.KClass](kotlin.reflect.-k-class/index.html) |  |

