package com.happn.upload.strongtyping

sealed class Photo(val filePath: String) {
    class ToResize(filePath: String) : Photo(filePath)

    class Resized(
        resizedFilePath: String,
        val widthPx: Int,
        val heightPx: Int,
    ) : Photo(resizedFilePath)

    class Uploaded(
        remoteUrl: String,
        val uploadHash: String,
    ) : Photo(remoteUrl)
}
