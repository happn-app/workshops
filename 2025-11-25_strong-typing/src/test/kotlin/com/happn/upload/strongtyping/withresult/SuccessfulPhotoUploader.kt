package com.happn.upload.strongtyping.withresult

import com.happn.upload.strongtyping.Photo
import com.happn.upload.strongtyping.withresult.error.UploaderError
import kotlin.random.Random

class SuccessfulPhotoUploader : PhotoUploader {
    override fun upload(photo: Photo.Resized): Result<Photo.Uploaded, UploaderError> {
        val uploadedPhoto = Photo.Uploaded(
            remoteUrl = photo.filePath,
            uploadHash = Random.nextInt().toString(),
        )
        return Result.Success(uploadedPhoto)
    }
}