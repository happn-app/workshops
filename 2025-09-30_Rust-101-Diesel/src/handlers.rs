//! # handlers.rs – Definition of HTTP routes (CRUD) for users
//!
//! This module handles the following routes:
//! - GET /users → List users
//! - POST /users → Create a user
//! - PUT /users/{id} → Update a user
//! - DELETE /users/{id} → Delete a user
// 📦 Required imports
use actix_web::{get, post, put, delete, web, HttpResponse, Responder}; // Route macros and web types
use diesel::prelude::*;                                     // SQL queries with Diesel
use crate::{models::{User, NewUser}, schema::users::dsl::*, db::DbPool, error::ApiError}; // Access to models, schema and DB, Custom error handling
use uuid::Uuid;                                              // For the user's unique identifier

/// ✅ Handler GET /users
/// Retrieves the list of users from the database
#[get("/users")]
async fn list_users(
    pool: web::Data<DbPool>, // Database connection pool
) -> Result<impl Responder, ApiError> {
    let conn = &mut pool.get().map_err(|_| ApiError::InternalError)?; // DB connection from the pool

    // 🔎 SELECT * FROM users
    let result = users
        .select(User::as_select()) // Selects all necessary columns
        .load::<User>(conn)        // Executes the query
        .map_err(ApiError::DbError)?; // Handles potential DB errors

    Ok(HttpResponse::Ok().json(result)) // 🔁 Returns the list as JSON
}

/// ✅ Handler POST /users
/// Creates a new user from received JSON
#[post("/users")]
async fn create_user(
    pool: web::Data<DbPool>, // Database connection pool
    item: Result<web::Json<NewUser>, actix_web::Error>,
) -> Result<impl Responder, ApiError> { // Result with custom error handling
    let conn = &mut pool.get().map_err(|_| ApiError::InternalError)?; // DB connection from the pool
    let item = item.map_err(|e| ApiError::BadRequest(e.to_string()))?; // Validates JSON input

    // 🔧 INSERT INTO users VALUES (...)
    diesel::insert_into(users)
        .values(&*item) // Inserts the new user
        .execute(conn) // Executes the insertion
        .map_err(ApiError::DbError)?; // Handles potential DB errors

    Ok(HttpResponse::Created().finish()) // ✅ Returns 201 Created
}

/// ✅ Handler PUT /users/{user_id}
/// Updates the name of the specified user
#[put("/users/{user_id}")]
async fn update_user(
    pool: web::Data<DbPool>, // Database connection pool
    user_id: web::Path<Uuid>, // Extracts user_id from the path
    item: Result<web::Json<NewUser>, actix_web::Error>,
) -> Result<impl Responder, ApiError> { // Result with custom error handling
    let conn = &mut pool.get().map_err(|_| ApiError::InternalError)?; // DB connection from the pool
    let item = item.map_err(|e| ApiError::BadRequest(e.to_string()))?; // Validates JSON input

    // 🔄 UPDATE users SET name = ... WHERE id = ...
    let updated = diesel::update(users.filter(id.eq(*user_id))) //Filters by user_id
        .set(name.eq(&item.name)) // Updates only the name field
        .execute(conn) // Executes the update
        .map_err(ApiError::DbError)?; // Handles potential DB errors

    if updated == 0 { // No rows were updated
        return Err(ApiError::NotFound);
    }

    Ok(HttpResponse::Ok().finish())
}

/// ✅ Handler DELETE /users/{user_id}
/// Deletes a user by their ID
#[delete("/users/{user_id}")]
async fn delete_user(
    pool: web::Data<DbPool>, // Database connection pool
    user_id: web::Path<Uuid>, // Extracts user_id from the path
) -> Result<impl Responder, ApiError> {
    let conn = &mut pool.get().map_err(|_| ApiError::InternalError)?; // DB connection from the pool

    // ❌ DELETE FROM users WHERE id = ...
    let deleted = diesel::delete(users.filter(id.eq(*user_id))) // Filters by user_id
        .execute(conn)// Executes the deletion
        .map_err(ApiError::DbError)?; // Handles potential DB errors

    if deleted == 0 { // No rows were deleted
        return Err(ApiError::NotFound); // Returns 404 Not Found
    }

    Ok(HttpResponse::NoContent().finish()) // ✅ Returns 204 No Content
}
