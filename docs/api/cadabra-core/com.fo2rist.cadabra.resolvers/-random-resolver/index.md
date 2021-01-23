---
title: RandomResolver - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra.resolvers](../index.html) / [RandomResolver](./index.html)

# RandomResolver

`class RandomResolver<V : `[`Variant`](../../com.fo2rist.cadabra/-variant/index.html)`> : `[`Resolver`](../../com.fo2rist.cadabra/-resolver/index.html)`<V>`

[Resolver](../../com.fo2rist.cadabra/-resolver/index.html) that picks random variant every time asked.
Can be useful for testing.

### Constructors

| [&lt;init&gt;](-init-.html) | `RandomResolver(variantsEnum: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<V>)`<br>[Resolver](../../com.fo2rist.cadabra/-resolver/index.html) that picks random variant every time asked. Can be useful for testing.`RandomResolver(variantsEnum: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<V>)` |

### Properties

| [variant](variant.html) | Get variant to be used now. Note that Cadabra calls this method every time variant resolution is required, so if the same variant should be provided for particular user/session/etc. make sure either cache it in the app and don't ask twice or implement receiver in a way it takes care of that. It's recommended to keep the app code free from the experiment-related code so caching inside he receiver is preferred.`val variant: V` |

