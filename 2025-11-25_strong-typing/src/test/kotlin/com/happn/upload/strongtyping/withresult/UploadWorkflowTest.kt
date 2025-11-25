package com.happn.upload.strongtyping.withresult

import com.happn.upload.strongtyping.Photo
import com.happn.upload.strongtyping.withresult.error.ResizerError
import com.happn.upload.strongtyping.withresult.error.UploaderError
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UploadWorkflowTest {
    private val workflow: UploadWorkflow = UploadWorkflow(
        resizer = SuccessfulPhotoResizer(),
        uploader = SuccessfulPhotoUploader(),
    )

    @Test
    fun `after calling upload, the photo is uploaded`() {
        val initialPhoto = Photo.ToResize("path/to/photo.jpg")

        val workflowResult = workflow.addPhoto(initialPhoto)
        when (workflowResult) {
            is Result.Failure -> when (workflowResult.error) {
                is ResizerError.FileNotFound -> TODO()
                is ResizerError.InvalidFormat -> TODO()
                is ResizerError.Unknown -> TODO()
                is UploaderError.InvalidFormat -> TODO()
                is UploaderError.Network -> TODO()
                is UploaderError.Unknown -> TODO()
            }
            is Result.Success -> {
                val uploadedPhoto = workflowResult.value
                assertTrue(uploadedPhoto is Photo.Uploaded)
                assertTrue(workflow.uploadedPhotos.contains(uploadedPhoto))
            }
        }
    }
}