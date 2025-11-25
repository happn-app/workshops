package com.happn.upload.weaktyping

interface PhotoResizer {
    fun resize(photo: SimplePhoto): SimplePhoto
}
