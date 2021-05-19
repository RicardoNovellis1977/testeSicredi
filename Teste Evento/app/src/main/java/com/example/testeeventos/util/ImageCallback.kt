package com.example.testeeventos.util

import com.squareup.picasso.Callback

abstract class ImageCallback : Callback {

    override fun onSuccess() {
        onAfterLoad()
    }

    override fun onError(e: Exception?) {
        onAfterLoad()
    }

    abstract fun onAfterLoad()
}