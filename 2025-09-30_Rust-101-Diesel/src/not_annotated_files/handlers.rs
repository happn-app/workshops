use actix_web::{get, post, put, delete, web, HttpResponse};
use diesel::prelude::*;
use crate::{models::{User, NewUser}, schema::users::dsl::*, db::DbPool};
use uuid::Uuid;

#[get("/users")]
async fn list_users(pool: web::Data<DbPool>) -> HttpResponse {
    let conn = &mut pool.get().unwrap();
    let result = users
        .select(User::as_select())
        .load::<User>(conn) // maintenant User implémente FromSqlRow<Pg>
        .unwrap();
    HttpResponse::Ok().json(result)
}

#[post("/users")]
async fn create_user(pool: web::Data<DbPool>, item: web::Json<NewUser>) -> HttpResponse {
    let conn = &mut pool.get().unwrap();
    diesel::insert_into(users)
        .values(&*item)
        .execute(conn)
        .unwrap();
    HttpResponse::Created().finish()
}

#[put("/users/{user_id}")]
async fn update_user(
    pool: web::Data<DbPool>,
    user_id: web::Path<Uuid>,
    item: web::Json<NewUser>
) -> HttpResponse {
    let conn = &mut pool.get().unwrap();
    diesel::update(users.filter(id.eq(user_id.into_inner())))
        .set(name.eq(&item.name))
        .execute(conn)
        .unwrap();
    HttpResponse::Ok().finish()
}

#[delete("/users/{user_id}")]
async fn delete_user(pool: web::Data<DbPool>, user_id: web::Path<Uuid>) -> HttpResponse {
    let conn = &mut pool.get().unwrap();
    diesel::delete(users.filter(id.eq(user_id.into_inner())))
        .execute(conn)
        .unwrap();
    HttpResponse::NoContent().finish()
}