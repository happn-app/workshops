//! # db.rs – Connexion à PostgreSQL avec Diesel + r2d2
//!
//! Ce module définit :
//! - Un alias de type `DbPool` pour simplifier les appels
//! - Une fonction `get_pool()` qui initialise le pool de connexions

// 📦 Imports des composants Diesel et r2d2 (gestion de pool de connexions)
use diesel::r2d2::{self, ConnectionManager}; // r2d2 = Rust Resource Pool
use diesel::pg::PgConnection;                // Type de connexion PostgreSQL

// 🧱 Alias de type pour simplifier la signature du pool
pub type DbPool = r2d2::Pool<ConnectionManager<PgConnection>>;

/// 🔌 Fonction pour initialiser le pool de connexions
/// 
/// # Arguments
/// - `database_url`: l'URL de connexion PostgreSQL (ex: depuis .env)
///
/// # Retour
/// - Un pool de connexions PostgreSQL prêtes à l'emploi
pub fn get_pool(database_url: &str) -> DbPool {
    // 🔧 Création d'un "manager" pour gérer les connexions Pg
    let manager = ConnectionManager::<PgConnection>::new(database_url);

    // 🏗️ Construction du pool via r2d2
    r2d2::Pool::builder()
        .build(manager)
        .expect("Failed to create pool.") // 🔥 Panique si la connexion échoue
}
