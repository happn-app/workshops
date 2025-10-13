use actix_web::{HttpResponse, ResponseError}; // Import ResponseError trait
use thiserror::Error; // For deriving the Error trait

#[derive(Debug, Error)]
pub enum ApiError {
    #[error("Resource not found")] // 404 Not Found variant
    NotFound,

    #[error("Database error")] // DB error variant
    DbError(#[from] diesel::result::Error),

    #[error("Internal server error")] // Generic internal error
    InternalError,

    #[error("Bad request: {0}")] // Include a message for bad requests
    BadRequest(String),
}

impl ResponseError for ApiError {
    fn error_response(&self) -> HttpResponse {
        match self {
            ApiError::NotFound => HttpResponse::NotFound().json("Not found"), // 404 response
            ApiError::DbError(_) => HttpResponse::InternalServerError().json("Database error"), // 500 response
            ApiError::BadRequest(msg) => HttpResponse::BadRequest().json(msg), // 400 response with message
            ApiError::InternalError => HttpResponse::InternalServerError().json("Internal error"), // 500 response
        }
    }
}
