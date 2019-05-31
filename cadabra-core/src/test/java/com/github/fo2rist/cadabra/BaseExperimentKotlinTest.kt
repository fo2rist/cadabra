package com.github.fo2rist.cadabra

import com.github.fo2rist.cadabra.testdata.SimpleVariants
import com.github.fo2rist.cadabra.testdata.SimpleVariants.A
import com.github.fo2rist.cadabra.testdata.SimpleVariants.B
import io.kotlintest.data.forall
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import io.kotlintest.tables.row

private val BASE_EXPERIMENT_OF_KOTLIN_CLASS =
    object : BaseExperiment<SimpleVariants>(SimpleVariants::class) {}
private val BASE_EXPERIMENT_OF_JAVA_CLASS =
    object : BaseExperiment<SimpleVariants>(SimpleVariants::class.java) {}

class BaseExperimentKotlinTest : WordSpec({
    "BaseExperiment" should {
        "give all enum items as variants" {
            forall(
                row(BASE_EXPERIMENT_OF_KOTLIN_CLASS),
                row(BASE_EXPERIMENT_OF_JAVA_CLASS)
            ) { testExperiment ->
                testExperiment.variants.size shouldBe 2
                testExperiment.variants shouldContainAll listOf(A, B)
            }
        }

        "uses class name as ID by default" {
            BASE_EXPERIMENT_OF_KOTLIN_CLASS.id shouldBe "BASE_EXPERIMENT_OF_KOTLIN_CLASS\$1" // anonymous class name
        }
    }
})
