package com.happn.upload.weaktyping

class UploadWorkflow(
    val resizer: PhotoResizer,
    val uploader: PhotoUploader,
) {
    val uploadedPhotos: MutableList<SimplePhoto> = mutableListOf()

    fun start() {
        // Start processing photos...
    }

    fun stop() {
        // Stop processing photos...
    }

    fun addPhoto(photo: SimplePhoto): SimplePhoto {
        // Warning: All photos are SimplePhoto.
        // It is very easy to pass the wrong type!
        val resizedPhoto: SimplePhoto = resizer.resize(photo)
        val uploadedPhoto: SimplePhoto = uploader.upload(resizedPhoto)

        uploadedPhotos.add(uploadedPhoto)

        return uploadedPhoto
    }

    fun allPhotosUploaded(): Boolean = uploadedPhotos.all { it.isUploaded() }
}

