package com.happn.upload.strongtyping.withresult

import com.happn.upload.strongtyping.Photo
import com.happn.upload.strongtyping.withresult.error.DomainError

class UploadWorkflow(
    val resizer: PhotoResizer,
    val uploader: PhotoUploader,
) {
    val uploadedPhotos: MutableList<Photo.Uploaded> = mutableListOf()

    fun start() {
        // Start processing photos...
    }

    fun stop() {
        // Stop processing photos...
    }

    fun addPhoto(photoToResize: Photo.ToResize): Result<Photo.Uploaded, DomainError> {
        val resizerResult = resizer.resize(photoToResize)
        return when (resizerResult) {
            is Result.Failure -> Result.Failure(resizerResult.error)
            is Result.Success -> handleResizeSuccess(resizerResult.value)
        }
    }

    private fun handleResizeSuccess(resizedPhoto: Photo.Resized): Result<Photo.Uploaded, DomainError> {
        val uploaderResult = uploader.upload(resizedPhoto)
        return when (uploaderResult) {
            is Result.Failure -> Result.Failure(uploaderResult.error)
            is Result.Success -> Result.Success(uploaderResult.value)
        }
    }
}

