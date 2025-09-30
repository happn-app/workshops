
mod db;
mod models;
mod schema;
mod handlers;

use actix_web::{App, HttpServer};
use dotenvy::dotenv;
use std::env;
use db::get_pool;
use handlers::*;

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    dotenv().ok();
    let db_url = env::var("DATABASE_URL").expect("DATABASE_URL not set");
    let pool = get_pool(&db_url);

    HttpServer::new(move || {
        App::new()
            .app_data(actix_web::web::Data::new(pool.clone()))
            .service(list_users)
            .service(create_user)
            .service(update_user)
            .service(delete_user)
    })
    .bind(("0.0.0.0", 8080))?
    .run()
    .await
}
