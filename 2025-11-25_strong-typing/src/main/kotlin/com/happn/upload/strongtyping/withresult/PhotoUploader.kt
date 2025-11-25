package com.happn.upload.strongtyping.withresult

import com.happn.upload.strongtyping.Photo
import com.happn.upload.strongtyping.withresult.error.UploaderError

interface PhotoUploader {
    fun upload(photo: Photo.Resized): Result<Photo.Uploaded, UploaderError>
}
