---
title: com.fo2rist.cadabra.resolvers - cadabra-core
---

[cadabra-core](../index.html) / [com.fo2rist.cadabra.resolvers](./index.html)

## Package com.fo2rist.cadabra.resolvers

Handy resolvers that can simplify testing Cadabra.

### Types

| [RandomResolver](-random-resolver/index.html) | [Resolver](../com.fo2rist.cadabra/-resolver/index.html) that picks random variant every time asked. Can be useful for testing.`class RandomResolver<V : `[`Variant`](../com.fo2rist.cadabra/-variant/index.html)`> : `[`Resolver`](../com.fo2rist.cadabra/-resolver/index.html)`<V>` |
| [StaticResolver](-static-resolver/index.html) | [Resolver](../com.fo2rist.cadabra/-resolver/index.html) that returns the same variant one constructed. Can be useful when the variant is defined for the lifetime of the app or for testing.`class StaticResolver<V : `[`Variant`](../com.fo2rist.cadabra/-variant/index.html)`> : `[`Resolver`](../com.fo2rist.cadabra/-resolver/index.html)`<V>` |

