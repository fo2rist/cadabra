---
title: Variant.name - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [Variant](index.html) / [name](./name.html)

# name

`abstract val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Name of the variant.
Given that variants are implemented as enums it should be always provided by [Enum.name](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/name.html)
with not implementation required for Kotlin.
For java implement `String getName()` by delegating to [java.lang.Enum.name](https://docs.oracle.com/javase/6/docs/api/java/lang/Enum.html#name()).

