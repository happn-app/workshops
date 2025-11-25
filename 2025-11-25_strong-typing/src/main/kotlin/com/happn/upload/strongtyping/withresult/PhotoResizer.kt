package com.happn.upload.strongtyping.withresult

import com.happn.upload.strongtyping.Photo
import com.happn.upload.strongtyping.withresult.error.ResizerError

interface PhotoResizer {
    fun resize(photoToResize: Photo.ToResize): Result<Photo.Resized, ResizerError>
}

