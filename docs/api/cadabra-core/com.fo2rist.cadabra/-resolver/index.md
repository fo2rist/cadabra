---
title: Resolver - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [Resolver](./index.html)

# Resolver

`interface Resolver<V : `[`Variant`](../-variant/index.html)`>`

Resolves the experiment by giving single Variant among available.

### Properties

| [variant](variant.html) | Get variant to be used now. Note that Cadabra calls this method every time variant resolution is required, so if the same variant should be provided for particular user/session/etc. make sure either cache it in the app and don't ask twice or implement receiver in a way it takes care of that. It's recommended to keep the app code free from the experiment-related code so caching inside he receiver is preferred.`abstract val variant: V` |

### Inheritors

| [RandomResolver](../../com.fo2rist.cadabra.resolvers/-random-resolver/index.html) | [Resolver](./index.html) that picks random variant every time asked. Can be useful for testing.`class RandomResolver<V : `[`Variant`](../-variant/index.html)`> : `[`Resolver`](./index.html)`<V>` |
| [StaticResolver](../../com.fo2rist.cadabra.resolvers/-static-resolver/index.html) | [Resolver](./index.html) that returns the same variant one constructed. Can be useful when the variant is defined for the lifetime of the app or for testing.`class StaticResolver<V : `[`Variant`](../-variant/index.html)`> : `[`Resolver`](./index.html)`<V>` |

