package com.happn.upload.strongtyping.withoutresult

import com.happn.upload.strongtyping.Photo
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UploadWorkflowTest {

    private val workflow: UploadWorkflow = UploadWorkflow(
        resizer = DummyPhotoResizer(),
        uploader = DummyPhotoUploader(),
    )

    @Test
    fun `after calling upload, the photo is uploaded`() {
        val initialPhoto = Photo.ToResize("path/to/photo.jpg")

        val uploadedPhoto = workflow.addPhoto(initialPhoto)

        assertTrue(uploadedPhoto is Photo.Uploaded)
        assertTrue(workflow.allPhotosUploaded())
    }
}