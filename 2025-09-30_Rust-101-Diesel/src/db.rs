//! # db.rs – Connection to PostgreSQL with Diesel + r2d2
//!
//! This module defines:
//! - A type alias `DbPool` to simplify calls
//! - A function `get_pool()` that initializes the connection pool

// 📦 Imports of Diesel and r2d2 components (connection pool management)
use diesel::r2d2::{self, ConnectionManager}; // r2d2 = Rust Resource Pool
use diesel::pg::PgConnection;                // PostgreSQL connection type

// 🧱 Type alias to simplify the pool signature
pub type DbPool = r2d2::Pool<ConnectionManager<PgConnection>>;

/// 🔌 Function to initialize the connection pool
/// 
/// # Arguments
/// - `database_url`: the PostgreSQL connection URL (e.g. from .env)
///
/// # Return
/// - A ready-to-use PostgreSQL connection pool
pub fn get_pool(database_url: &str) -> DbPool {
    // 🔧 Create a "manager" to handle Pg connections
    let manager = ConnectionManager::<PgConnection>::new(database_url);

    // 🏗️ Build the pool via r2d2
    r2d2::Pool::builder()
        .build(manager)
        .expect("Failed to create pool.") // 🔥 Panic if connection fails
}