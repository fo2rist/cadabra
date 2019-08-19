package com.fo2rist.cadabra.android

import com.fo2rist.cadabra.android.testdata.SimpleAndroidExperiment
import com.fo2rist.cadabra.android.testdata.createAppContextMock
import com.fo2rist.cadabra.android.testdata.createResourcesMock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.kotlintest.TestCase
import io.kotlintest.specs.WordSpec

private val TEST_VARIANT = SimpleAndroidExperiment.B

private lateinit var resourcesResolverMock : ResourcesResolver

private lateinit var variantResources: VariantResources

class VariantResourcesImplKotlinTest : WordSpec({
    "getString" should {
        "resolves string" {
            variantResources.getString(0)

            verify(resourcesResolverMock).resolveStringResource(any(), any(), eq(TEST_VARIANT.name))
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        resourcesResolverMock = mock()

        variantResources = VariantResourcesImpl(
            createAppContextMock(createResourcesMock()),
            TEST_VARIANT,
            resourcesResolverMock)
    }
}
