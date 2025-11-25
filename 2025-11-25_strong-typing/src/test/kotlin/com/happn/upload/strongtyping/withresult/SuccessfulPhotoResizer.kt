package com.happn.upload.strongtyping.withresult

import com.happn.upload.strongtyping.Photo
import com.happn.upload.strongtyping.withresult.error.ResizerError

class SuccessfulPhotoResizer : PhotoResizer {
    override fun resize(photoToResize: Photo.ToResize): Result<Photo.Resized, ResizerError> {
        val resizedPhoto = Photo.Resized(
            resizedFilePath = photoToResize.filePath + "_resized",
            widthPx = 100,
            heightPx = 100,
        )
        return Result.Success(resizedPhoto)
    }
}