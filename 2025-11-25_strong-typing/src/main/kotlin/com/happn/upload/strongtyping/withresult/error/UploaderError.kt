package com.happn.upload.strongtyping.withresult.error

import com.happn.upload.strongtyping.Photo

sealed class UploaderError(val photo: Photo.Resized, val reason: String?) : DomainError {
    class Unknown(photo: Photo.Resized, reason: String? = null) : UploaderError(photo, reason)
    class Network(photo: Photo.Resized, reason: String? = null) : UploaderError(photo, reason)
    class InvalidFormat(photo: Photo.Resized, reason: String? = null) : UploaderError(photo, reason)
}