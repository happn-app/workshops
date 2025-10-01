//! # handlers.rs – Definition of HTTP routes (CRUD) for users
//!
//! This module handles the following routes:
//! - GET /users         → List users
//! - POST /users        → Create a user
//! - PUT /users/{id}    → Update a user
//! - DELETE /users/{id} → Delete a user
// 📦 Required imports
use actix_web::{get, post, put, delete, web, HttpResponse}; // Route macros and web types
use diesel::prelude::*;                                     // SQL queries with Diesel
use crate::{models::{User, NewUser}, schema::users::dsl::*, db::DbPool}; // Access to models, schema and DB
use uuid::Uuid;                                              // For the user's unique identifier

/// ✅ Handler GET /users
/// Retrieves the list of users from the database
#[get("/users")]
async fn list_users(pool: web::Data<DbPool>) -> HttpResponse {
    let conn = &mut pool.get().unwrap(); // DB connection from the pool

    // 🔎 SELECT * FROM users
    let result = users
        .select(User::as_select()) // Selects all necessary columns
        .load::<User>(conn)        // Executes the query
        .unwrap();

    HttpResponse::Ok().json(result) // 🔁 Returns the list as JSON
}

/// ✅ Handler POST /users
/// Creates a new user from received JSON
#[post("/users")]
async fn create_user(pool: web::Data<DbPool>, item: web::Json<NewUser>) -> HttpResponse {
    let conn = &mut pool.get().unwrap();

    // 🔧 INSERT INTO users VALUES (...)
    diesel::insert_into(users)
        .values(&*item)
        .execute(conn)
        .unwrap();

    HttpResponse::Created().finish() // ✅ Returns 201 Created
}

/// ✅ Handler PUT /users/{user_id}
/// Updates the name of the specified user
#[put("/users/{user_id}")]
async fn update_user(
    pool: web::Data<DbPool>,
    user_id: web::Path<Uuid>,
    item: web::Json<NewUser>
) -> HttpResponse {
    let conn = &mut pool.get().unwrap();

    // 🔄 UPDATE users SET name = ... WHERE id = ...
    diesel::update(users.filter(id.eq(user_id.into_inner())))
        .set(name.eq(&item.name))
        .execute(conn)
        .unwrap();

    HttpResponse::Ok().finish()
}

/// ✅ Handler DELETE /users/{user_id}
/// Deletes a user by their ID
#[delete("/users/{user_id}")]
async fn delete_user(pool: web::Data<DbPool>, user_id: web::Path<Uuid>) -> HttpResponse {
    let conn = &mut pool.get().unwrap();

    // ❌ DELETE FROM users WHERE id = ...
    diesel::delete(users.filter(id.eq(user_id.into_inner())))
        .execute(conn)
        .unwrap();

    HttpResponse::NoContent().finish() // ✅ Returns 204 No Content
}
