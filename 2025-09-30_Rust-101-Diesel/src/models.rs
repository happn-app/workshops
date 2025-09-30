//! # models.rs – Représentation des entités de la base de données
//!
//! Ce module définit :
//! - `User` : un utilisateur complet en base
//! - `NewUser` : un utilisateur à insérer (sans id)
//!
//! Ces structures sont utilisées pour :
//! - sérialiser/désérialiser en JSON (Actix)
//! - interagir avec PostgreSQL (Diesel)

use serde::{Serialize, Deserialize};   // Pour JSON <-> struct Rust
use diesel::prelude::*;               // Diesel ORM
use crate::schema::users;             // Schéma généré (ou défini) pour la table `users`
use uuid::Uuid;                       // Identifiant unique

/// ✅ Représente une ligne dans la table `users`
///
/// Utilisé pour :
/// - Lire des données de la base (`Queryable`)
/// - Retourner des données au format JSON (`Serialize`)
#[derive(Debug, Serialize, Deserialize, Queryable, Selectable, Identifiable)]
#[diesel(table_name = users)]
#[diesel(check_for_backend(diesel::pg::Pg))] // Indique qu'on utilise PostgreSQL
pub struct User {
    pub id: Uuid,       // 🆔 UUID (clé primaire)
    pub name: String,   // 👤 Nom de l'utilisateur
}

/// 🆕 Structure utilisée pour insérer un nouvel utilisateur
///
/// Utilisé pour :
/// - Créer un utilisateur via POST/PUT
/// - Sérialiser du JSON vers Rust
/// - Insérer en base via Diesel (`Insertable`)
#[derive(Debug, Serialize, Deserialize, Insertable)]
#[diesel(table_name = users)]
pub struct NewUser {
    pub name: String,   // 👤 Nom uniquement, sans ID
}
