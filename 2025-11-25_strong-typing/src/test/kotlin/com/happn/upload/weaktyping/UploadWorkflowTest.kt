package com.happn.upload.weaktyping

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UploadWorkflowTest {

    private val workflow: UploadWorkflow = UploadWorkflow(
        resizer = DummyPhotoResizer(),
        uploader = DummyPhotoUploader(),
    )

    @Test
    fun `after calling upload, the photo is uploaded`() {
        val photoToUpload = SimplePhoto("path/to/photo.jpg")

        val uploadedPhoto = workflow.addPhoto(photoToUpload)

        assertTrue(uploadedPhoto.isUploaded())
        assertTrue(workflow.allPhotosUploaded())
    }

    @Test
    fun `after calling upload, upload can be altered`() {
        val photoToUpload = SimplePhoto("path/to/photo.jpg")

        val uploadedPhoto = workflow.addPhoto(photoToUpload)

        // Altering the uploaded hash
        uploadedPhoto.uploadedHash = null

        // Yeah, the code is broken!
        assertFalse(uploadedPhoto.isUploaded())
        assertFalse(workflow.allPhotosUploaded())
    }
}