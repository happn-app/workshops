use serde::{Serialize, Deserialize};
use diesel::prelude::*;
use crate::schema::users;
use uuid::Uuid;

#[derive(Debug, Serialize, Deserialize, Queryable, Selectable, Identifiable)]
#[diesel(table_name = users)]
#[diesel(check_for_backend(diesel::pg::Pg))] // <-- indispensable
pub struct User {
    pub id: Uuid,
    pub name: String,
}

#[derive(Debug, Serialize, Deserialize, Insertable)]
#[diesel(table_name = users)]
pub struct NewUser {
    pub name: String,
}
