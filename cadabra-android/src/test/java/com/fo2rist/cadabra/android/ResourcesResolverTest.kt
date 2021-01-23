package com.fo2rist.cadabra.android

import com.fo2rist.cadabra.android.test.R
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
class ResourcesResolverTest {
    private val context by lazy { RuntimeEnvironment.application }
    private lateinit var namesGeneratorMock: ResourceNamesGenerator
    private lateinit var resourcesResolver: ResourcesResolver

    @Before
    fun setUp() {
        namesGeneratorMock = mock()
        resourcesResolver = ResourcesResolver(namesGeneratorMock)
    }

    private fun mockResolvedResourceName(result: String) {
        whenever(namesGeneratorMock.generateResourceName(any(), any())).thenReturn(result)
    }

    @Test
    fun `resolveStringResource gives R-string-RESOLVED_NAME for existing name`() {
        mockResolvedResourceName("test_b")

        assertEquals(R.string.test_b,
            resourcesResolver.resolveStringResource(context, R.string.test_a, "not_used_by_mocked_name_generator")
        )
    }

    @Test
    fun `resolveStringResource gives R-string-DEFAULT resource for nonexistent name`() {
        mockResolvedResourceName("test_nonexistent")

        assertEquals(R.string.test_a,
            resourcesResolver.resolveStringResource(context, R.string.test_a, "not_used_by_mocked_name_generator")
        )
    }

    @Test
    fun `resolveLayoutResource gives R-layout-RESOLVED_NAME for existing name`() {
        mockResolvedResourceName("test_b")

        assertEquals(
            R.layout.test_b,
            resourcesResolver.resolveLayoutResource(context, R.layout.test_a, "not_used_by_mocked_name_generator")
        )
    }

    @Test
    fun `resolveLayoutResource gives R-layout-DEFAULT resource for nonexistent name`() {
        mockResolvedResourceName("test_nonexistent")

        assertEquals(R.layout.test_a,
            resourcesResolver.resolveLayoutResource(context, R.layout.test_a, "not_used_by_mocked_name_generator")
        )
    }
}
