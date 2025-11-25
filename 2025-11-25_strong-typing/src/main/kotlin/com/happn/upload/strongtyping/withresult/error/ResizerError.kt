package com.happn.upload.strongtyping.withresult.error

import com.happn.upload.strongtyping.Photo

sealed class ResizerError(val photoToResize: Photo.ToResize, val reason: String?) : DomainError {
    class Unknown(photoToResize: Photo.ToResize, reason: String? = null) : ResizerError(photoToResize, reason)
    class FileNotFound(photoToResize: Photo.ToResize, reason: String? = null) : ResizerError(photoToResize, reason)
    class InvalidFormat(photoToResize: Photo.ToResize, reason: String? = null) : ResizerError(photoToResize, reason)
}