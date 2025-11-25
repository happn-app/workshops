package com.happn.upload.weaktyping

class DummyPhotoResizer : PhotoResizer {
    override fun resize(photo: SimplePhoto): SimplePhoto {
        return photo.apply { filePath = "${filePath}_resized" }
    }
}