package com.happn.upload.strongtyping.withoutresult

import com.happn.upload.strongtyping.Photo

class DummyPhotoResizer : PhotoResizer {
    override fun resize(photoToResize: Photo.ToResize): Photo.Resized {
        return Photo.Resized(
            resizedFilePath = photoToResize.filePath + "_resized",
            widthPx = 100,
            heightPx = 100,
        )
    }
}