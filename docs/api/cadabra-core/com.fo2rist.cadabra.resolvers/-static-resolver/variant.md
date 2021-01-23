---
title: StaticResolver.variant - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra.resolvers](../index.html) / [StaticResolver](index.html) / [variant](./variant.html)

# variant

`val variant: V`

Get variant to be used now.
Note that Cadabra calls this method every time variant resolution is required, so if the same variant should be
provided for particular user/session/etc. make sure either cache it in the app and don't ask twice or
implement receiver in a way it takes care of that.
It's recommended to keep the app code free from the experiment-related code so caching inside he receiver is
preferred.

