package com.happn.upload.weaktyping

import kotlin.random.Random

class DummyPhotoUploader : PhotoUploader {
    override fun upload(photo: SimplePhoto): SimplePhoto {
        return photo.apply { uploadedHash = Random.nextInt().toString() }
    }
}