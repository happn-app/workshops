//! # main.rs – Point d'entrée de l'application Actix-web avec PostgreSQL
//!
//! Ce fichier configure :
//! - Le serveur HTTP avec actix-web
//! - Le pool de connexions PostgreSQL
//! - Le chargement des variables d’environnement
//! - L’injection des routes de l’API

// 🔧 Modules internes
mod db;         // Connexion à la base de données
mod models;     // Structures de données (ex: User)
mod schema;     // Mapping base de données (généré ou manuel)
mod handlers;   // Fonctions HTTP pour les routes

// 📦 Imports externes
use actix_web::{App, HttpServer}; // Serveur web Actix
use dotenvy::dotenv;              // Chargement des variables d’environnement
use std::env;                     // Accès aux variables d’env
use db::get_pool;                 // Fonction utilitaire pour init le pool DB
use handlers::*;                  // Import de tous les endpoints (list, create, etc.)

/// 🚀 Fonction principale – async car le serveur est asynchrone
#[actix_web::main]
async fn main() -> std::io::Result<()> {
    // ✅ Chargement du fichier .env (ex: DATABASE_URL)
    dotenv().ok();

    // 🛠️ Récupération de l’URL de la base depuis les variables d’environnement
    let db_url = env::var("DATABASE_URL").expect("DATABASE_URL not set");

    // 🔌 Initialisation du pool de connexions PostgreSQL
    let pool = get_pool(&db_url);

    // 🌐 Démarrage du serveur Actix-web
    HttpServer::new(move || {
        App::new()
            // 🧠 Injection du pool de DB dans le contexte de l’app
            .app_data(actix_web::web::Data::new(pool.clone()))
            // 🔗 Enregistrement des endpoints
            .service(list_users)
            .service(create_user)
            .service(update_user)
            .service(delete_user)
    })
    // 🌍 Écoute sur toutes les interfaces (0.0.0.0), port 8080
    .bind(("0.0.0.0", 8080))?
    .run()
    .await
}