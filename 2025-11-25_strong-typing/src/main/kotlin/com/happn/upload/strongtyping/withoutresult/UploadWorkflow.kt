package com.happn.upload.strongtyping.withoutresult

import com.happn.upload.strongtyping.Photo

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

    fun addPhoto(photoToResize: Photo.ToResize): Photo.Uploaded {
        val resizedPhoto = resizer.resize(photoToResize)
        val uploadedPhoto = uploader.upload(resizedPhoto)
        return uploadedPhoto
            .also { uploadedPhotos.add(it) }
    }

    fun allPhotosUploaded(): Boolean = uploadedPhotos.all { it is Photo.Uploaded }
}

