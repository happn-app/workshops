package com.happn.upload.strongtyping.withoutresult

import com.happn.upload.strongtyping.Photo

interface PhotoResizer {
    fun resize(photoToResize: Photo.ToResize): Photo.Resized
}


