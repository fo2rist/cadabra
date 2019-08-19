package com.fo2rist.cadabra.firebase.helpers

import android.app.Activity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import java.util.concurrent.Executor

/**
 * Completes immediately and calls callbacks immediately when addXxxListener called.
 */
class ImmediateTask<T> : Task<T>() {
    override fun isComplete(): Boolean {
        return true
    }

    override fun getException(): Exception? {
        return null
    }

    override fun addOnFailureListener(p0: OnFailureListener): Task<T> {
        return this
    }

    override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<T> {
        return this
    }

    override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<T> {
        return this
    }

    override fun getResult(): T? {
        return null
    }

    override fun <X : Throwable?> getResult(p0: Class<X>): T? {
        return null
    }

    override fun addOnSuccessListener(p0: OnSuccessListener<in T>): Task<T> {
        p0.onSuccess(null)
        return this
    }

    override fun addOnSuccessListener(p0: Executor, p1: OnSuccessListener<in T>): Task<T> {
        p1.onSuccess(null)
        return this
    }

    override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in T>): Task<T> {
        p1.onSuccess(null)
        return this
    }

    override fun isSuccessful(): Boolean {
        return true
    }

    override fun isCanceled(): Boolean {
        return false
    }

    override fun addOnCompleteListener(p0: OnCompleteListener<T>): Task<T> {
        p0.onComplete(this)
        return this
    }

    override fun addOnCompleteListener(p0: Activity, p1: OnCompleteListener<T>): Task<T> {
        p1.onComplete(this)
        return this
    }

    override fun addOnCompleteListener(p0: Executor, p1: OnCompleteListener<T>): Task<T> {
        p1.onComplete(this)
        return this
    }
}
