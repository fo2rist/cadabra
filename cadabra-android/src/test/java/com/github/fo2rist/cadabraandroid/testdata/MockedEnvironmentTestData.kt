package com.github.fo2rist.cadabraandroid.testdata

import android.content.Context
import android.content.res.Resources
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.mockito.Mockito.RETURNS_DEEP_STUBS

internal fun createAppContextMock(resources: Resources? = null): Context {
    return mock<Context>(defaultAnswer = RETURNS_DEEP_STUBS).also {
        whenever(it.applicationContext).thenReturn(it)
        whenever(it.getString(any())).thenReturn("")
        whenever(it.packageName).thenReturn("com.github.fo2rist.cadabraandroid")
        if (resources != null){
            whenever(it.resources).thenReturn(resources)
        }
    }
}

internal const val DEFAULT_RES_PREFIX = "resource_"
internal const val DEFAULT_RES_NAME = "${DEFAULT_RES_PREFIX}a"
internal fun createResourcesMock(defaultResourceName: String = DEFAULT_RES_NAME): Resources {
    return mock<Resources>().also {
        whenever(it.getResourceEntryName(any())).thenReturn(defaultResourceName)
        whenever(it.getString(any())).thenReturn("")
    }
}
