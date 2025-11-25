package com.happn.upload.weaktyping

interface PhotoUploader {
    fun upload(photo: SimplePhoto): SimplePhoto
}
