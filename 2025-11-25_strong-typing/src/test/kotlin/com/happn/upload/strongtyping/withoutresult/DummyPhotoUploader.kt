package com.happn.upload.strongtyping.withoutresult

import com.happn.upload.strongtyping.Photo
import kotlin.random.Random

class DummyPhotoUploader : PhotoUploader {
    override fun upload(photo: Photo.Resized): Photo.Uploaded {
        return Photo.Uploaded(
            remoteUrl = photo.filePath,
            uploadHash = Random.nextInt().toString(),
        )
    }
}