package com.rzsahu.quotenice

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class Observer: DefaultLifecycleObserver {

    private val tag = "QuoteNice.Observer"

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.i(tag, "onCreate: observer")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.i(tag, "onStart: observer")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.i(tag, "onResume: observer")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.i(tag, "onPause: observer")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.i(tag, "onStop: observer")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.i(tag, "onDestroy: observer")
    }
}