---
title: Variant - cadabra-core
---

[cadabra-core](../../index.html) / [com.fo2rist.cadabra](../index.html) / [Variant](./index.html)

# Variant

`interface Variant`

A single option (variant) of the particular experiment.
Implement this interface by the enum so that each item represents the experiment variant,
and provide experimental parameters via properties/functions of that enum.

Hint: if experiment controls multiple parameter declare them as a single data-class or factory to simplify variants
enum class.

### Properties

| [name](name.html) | Name of the variant. Given that variants are implemented as enums it should be always provided by [Enum.name](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/name.html) with not implementation required for Kotlin. For java implement `String getName()` by delegating to [java.lang.Enum.name](https://docs.oracle.com/javase/6/docs/api/java/lang/Enum.html#name()).`abstract val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

