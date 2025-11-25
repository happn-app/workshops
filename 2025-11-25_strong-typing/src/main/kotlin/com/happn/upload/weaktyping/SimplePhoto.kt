package com.happn.upload.weaktyping

class SimplePhoto(var filePath: String) {
    var uploadedHash: String? = null

    fun isUploaded(): Boolean = uploadedHash?.isNotBlank() ?: false

    fun getUploadedUrl(): String? = uploadedHash?.let { "serverURL/$it" }
}