package com.happn.upload.strongtyping.withoutresult

import com.happn.upload.strongtyping.Photo

interface PhotoUploader {
    fun upload(photo: Photo.Resized): Photo.Uploaded
}

