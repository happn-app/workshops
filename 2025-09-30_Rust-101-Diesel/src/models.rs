//! # models.rs – Database entity representations
//!
//! This module defines:
//! - `User`: a complete user in the database
//! - `NewUser`: a user to insert (without id)
//!
//! These structures are used for:
//! - serializing/deserializing to/from JSON (Actix)
//! - interacting with PostgreSQL (Diesel)
use serde::{Serialize, Deserialize};   // Pour JSON <-> struct Rust
use diesel::prelude::*;               // Diesel ORM
use crate::schema::users;             // Schéma généré (ou défini) pour la table `users`
use uuid::Uuid;                       // Identifiant unique

/// ✅ Represents a row in the `users` table
///
/// Used for:
/// - Reading data from the database (`Queryable`)
/// - Returning data as JSON (`Serialize`)
#[derive(Debug, Serialize, Deserialize, Queryable, Selectable, Identifiable)]
#[diesel(table_name = users)]
#[diesel(check_for_backend(diesel::pg::Pg))] // Indique qu'on utilise PostgreSQL
pub struct User {
    pub id: Uuid,       // 🆔 UUID (primary key)
    pub name: String,   // 👤 User name
}

/// 🆕 Structure used to insert a new user
///
/// Used for:
/// - Creating a user via POST/PUT
/// - Serializing JSON to Rust
/// - Inserting into the database via Diesel (`Insertable`)
#[derive(Debug, Serialize, Deserialize, Insertable)]
#[diesel(table_name = users)]
pub struct NewUser {
    pub name: String,   // 👤 Name only, no ID
}
