//! # handlers.rs – Définition des routes HTTP (CRUD) pour les utilisateurs
//!
//! Ce module gère les routes suivantes :
//! - GET /users         → Liste des utilisateurs
//! - POST /users        → Création d'un utilisateur
//! - PUT /users/{id}    → Mise à jour d'un utilisateur
//! - DELETE /users/{id} → Suppression d'un utilisateur

// 📦 Imports nécessaires
use actix_web::{get, post, put, delete, web, HttpResponse}; // Macros de routes et types web
use diesel::prelude::*;                                     // Requêtes SQL avec Diesel
use crate::{models::{User, NewUser}, schema::users::dsl::*, db::DbPool}; // Accès aux modèles, schéma et DB
use uuid::Uuid;                                              // Pour l'identifiant unique des users

/// ✅ Handler GET /users
/// Récupère la liste des utilisateurs depuis la base
#[get("/users")]
async fn list_users(pool: web::Data<DbPool>) -> HttpResponse {
    let conn = &mut pool.get().unwrap(); // Connexion DB à partir du pool

    // 🔎 SELECT * FROM users
    let result = users
        .select(User::as_select()) // Sélectionne toutes les colonnes nécessaires
        .load::<User>(conn)        // Exécute la requête
        .unwrap();

    HttpResponse::Ok().json(result) // 🔁 Retourne la liste au format JSON
}

/// ✅ Handler POST /users
/// Crée un nouvel utilisateur à partir d'un JSON reçu
#[post("/users")]
async fn create_user(pool: web::Data<DbPool>, item: web::Json<NewUser>) -> HttpResponse {
    let conn = &mut pool.get().unwrap();

    // 🔧 INSERT INTO users VALUES (...)
    diesel::insert_into(users)
        .values(&*item)
        .execute(conn)
        .unwrap();

    HttpResponse::Created().finish() // ✅ Retourne 201 Created
}

/// ✅ Handler PUT /users/{user_id}
/// Met à jour le nom de l'utilisateur spécifié
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
/// Supprime un utilisateur par son ID
#[delete("/users/{user_id}")]
async fn delete_user(pool: web::Data<DbPool>, user_id: web::Path<Uuid>) -> HttpResponse {
    let conn = &mut pool.get().unwrap();

    // ❌ DELETE FROM users WHERE id = ...
    diesel::delete(users.filter(id.eq(user_id.into_inner())))
        .execute(conn)
        .unwrap();

    HttpResponse::NoContent().finish() // ✅ Retourne 204 No Content
}
