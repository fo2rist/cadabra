---
title: StaticResolver - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra.resolvers](../index.html) / [StaticResolver](./index.html)

# StaticResolver

`class StaticResolver<V : `[`Variant`](../../com.fo2rist.cadabra/-variant/index.html)`> : `[`Resolver`](../../com.fo2rist.cadabra/-resolver/index.html)`<V>`

[Resolver](../../com.fo2rist.cadabra/-resolver/index.html) that returns the same variant one constructed.
Can be useful when the variant is defined for the lifetime of the app or for testing.

### Constructors

| [&lt;init&gt;](-init-.html) | [Resolver](../../com.fo2rist.cadabra/-resolver/index.html) that returns the same variant one constructed. Can be useful when the variant is defined for the lifetime of the app or for testing.`StaticResolver(variant: V)` |

### Properties

| [variant](variant.html) | Get variant to be used now. Note that Cadabra calls this method every time variant resolution is required, so if the same variant should be provided for particular user/session/etc. make sure either cache it in the app and don't ask twice or implement receiver in a way it takes care of that. It's recommended to keep the app code free from the experiment-related code so caching inside he receiver is preferred.`val variant: V` |

