package com.github.fo2rist.cadabraandroid

import android.content.Context
import android.content.res.Resources
import com.github.fo2rist.cadabraandroid.testdata.DEFAULT_RES_PREFIX
import com.github.fo2rist.cadabraandroid.testdata.SimpleAndroidVariants
import com.github.fo2rist.cadabraandroid.testdata.createAppContextMock
import com.github.fo2rist.cadabraandroid.testdata.createResourcesMock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotlintest.TestCase
import io.kotlintest.specs.WordSpec

private lateinit var appContextMock: Context
private lateinit var resourcesMock: Resources

private lateinit var variantResources: VariantResources

class VariantResourcesImplKotlinTest : WordSpec({
    "getString" should {
        "get id of resource with proper generated name" {
            variantResources.getString(0)

            verify(resourcesMock).getIdentifier(eq("${DEFAULT_RES_PREFIX}b"), any(), any())
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        appContextMock = createAppContextMock()
        resourcesMock = createResourcesMock()
        whenever(appContextMock.resources).thenReturn(resourcesMock)

        variantResources = VariantResourcesImpl(
            appContextMock,
            SimpleAndroidVariants.B
        )
    }
}
