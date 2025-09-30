//! # main.rs – Actix-web application entry point with PostgreSQL
//!
//! This file configures:
//! - The HTTP server with actix-web
//! - The PostgreSQL connection pool
//! - Loading environment variables
//! - API route injection

// 🔧 Internal modules
mod db;         // Database connection
mod models;     // Data structures (e.g., User)
mod schema;     // Database mapping (generated or manual)
mod handlers;   // HTTP functions for routes

// 📦 External imports
use actix_web::{App, HttpServer}; // Actix web server
use dotenvy::dotenv;              // Loading environment variables
use std::env;                     // Access to env variables
use db::get_pool;                 // Utility function to init DB pool
use handlers::*;                  // Import all endpoints (list, create, etc.)

/// 🚀 Main function – async because the server is asynchronous
#[actix_web::main]
async fn main() -> std::io::Result<()> {
    // ✅ Load the .env file (e.g., DATABASE_URL)
    dotenv().ok();

    // 🛠️ Retrieve the database URL from environment variables
    let db_url = env::var("DATABASE_URL").expect("DATABASE_URL not set");

    // 🔌 Initialize the PostgreSQL connection pool
    let pool = get_pool(&db_url);

    // 🌐 Start the Actix-web server
    HttpServer::new(move || {
        App::new()
            // 🧠 Inject the DB pool into the app context
            .app_data(actix_web::web::Data::new(pool.clone()))
            // 🔗 Register endpoints
            .service(list_users)
            .service(create_user)
            .service(update_user)
            .service(delete_user)
    })
    // 🌍 Listen on all interfaces (0.0.0.0), port 8080
    .bind(("0.0.0.0", 8080))?
    .run()
    .await
}