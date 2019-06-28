package com.github.fo2rist.cadabra.firebase.helpers

import android.app.Activity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import java.util.concurrent.Executor

/**
 * Completes immediately and calls callbacks immediately when addXxxListener called.
 */
class ImmediateTask : Task<Boolean>() {
    override fun isComplete(): Boolean {
        return true
    }

    override fun getException(): Exception? {
        return null
    }

    override fun addOnFailureListener(p0: OnFailureListener): Task<Boolean> {
        return this
    }

    override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<Boolean> {
        return this
    }

    override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<Boolean> {
        return this
    }

    override fun getResult(): Boolean? {
        return true
    }

    override fun <X : Throwable?> getResult(p0: Class<X>): Boolean? {
        return true
    }

    override fun addOnSuccessListener(p0: OnSuccessListener<in Boolean>): Task<Boolean> {
        p0.onSuccess(true)
        return this
    }

    override fun addOnSuccessListener(p0: Executor, p1: OnSuccessListener<in Boolean>): Task<Boolean> {
        p1.onSuccess(true)
        return this
    }

    override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in Boolean>): Task<Boolean> {
        p1.onSuccess(true)
        return this
    }

    override fun isSuccessful(): Boolean {
        return true
    }

    override fun isCanceled(): Boolean {
        return false
    }

    override fun addOnCompleteListener(p0: OnCompleteListener<Boolean>): Task<Boolean> {
        p0.onComplete(this)
        return this
    }

    override fun addOnCompleteListener(p0: Activity, p1: OnCompleteListener<Boolean>): Task<Boolean> {
        p1.onComplete(this)
        return this
    }

    override fun addOnCompleteListener(p0: Executor, p1: OnCompleteListener<Boolean>): Task<Boolean> {
        p1.onComplete(this)
        return this
    }
}
