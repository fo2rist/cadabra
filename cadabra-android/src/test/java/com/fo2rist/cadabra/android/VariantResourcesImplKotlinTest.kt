package com.fo2rist.cadabra.android

import com.fo2rist.cadabra.android.testdata.SimpleAndroidExperiment
import com.fo2rist.cadabra.android.testdata.createAppContextMock
import com.fo2rist.cadabra.android.testdata.createResourcesMock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.isNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.kotest.core.test.TestCase
import io.kotest.core.spec.style.WordSpec

private val TEST_VARIANT = SimpleAndroidExperiment.B

private lateinit var resourcesResolverMock : ResourcesResolver

private lateinit var normalVariantResources: VariantResources
private lateinit var nullVariantResources: VariantResources

class VariantResourcesImplKotlinTest : WordSpec({

    "resource for experimental variant" should {

        "resolve getString through resolver's resolveStringResource with variant name" {
            normalVariantResources.getString(0)

            verify(resourcesResolverMock).resolveStringResource(any(), any(), eq(TEST_VARIANT.name))
        }

        "resolve getLayout through resolver's resolveLayoutResource with variant name" {
            normalVariantResources.getLayoutId(0)

            verify(resourcesResolverMock).resolveLayoutResource(any(), any(), eq(TEST_VARIANT.name))
        }
    }

    "resource for null variant" should {

        "resolve getString through resolver's resolveStringResource with null" {
            nullVariantResources.getString(0)

            verify(resourcesResolverMock).resolveStringResource(any(), any(), isNull())
        }

        "resolve getLayout through resolver's resolveLayoutResource with null" {
            nullVariantResources.getLayoutId(0)

            verify(resourcesResolverMock).resolveLayoutResource(any(), any(), isNull())
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        resourcesResolverMock = mock()

        normalVariantResources = VariantResourcesImpl(
            createAppContextMock(createResourcesMock()),
            TEST_VARIANT,
            resourcesResolverMock)

        nullVariantResources = VariantResourcesImpl(
            createAppContextMock(createResourcesMock()),
            null,
            resourcesResolverMock
        )
    }
}
