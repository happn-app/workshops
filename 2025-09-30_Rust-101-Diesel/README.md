# 🚀 Workshop Rust + Actix + Diesel + Postgres

## 0. 🛠️ Install Rust and use Cargo

### Installing Rust
Rust is installed via **rustup** (the official manager):
```bash
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
```
Then reload your terminal:
```bash
source $HOME/.cargo/env
```

Check the installation:
```bash
rustc --version
cargo --version
```

### Cargo: Rust Project Manager
Cargo is used to:
- **Dependency management** (`Cargo.toml`)
- **Compilation** (`cargo build`)
- **Execution** (`cargo run`)
- **Testing** (`cargo test`)
- **Documentation** (`cargo doc --open`)
- **Auditing** (`cargo audit` si installé)
- **Formatting** (`cargo fmt`)
- **Code quality** (`cargo clippy`)

### Typical Files
- `Cargo.toml`: project configuration
- `Cargo.lock`: exact dependency versions
- `src/main.rs`: entry point
- `src/lib.rs`: library (optional)
- `target/`: compiled binaries

### Typical Workflow
```bash
cargo new hello-rust
cd hello-rust
cargo build
cargo run
cargo test
cargo fmt
cargo clippy
```

### Project Dependencies (Diesel + Actix)
```toml
actix-web = "4" # Async web framework based on Actix
actix-rt = "2" # Lightweight runtime based on Actix
serde = { version = "1", features = ["derive"] } # JSON <-> Rust structs
serde_json = "1" # JSON handling
diesel = { version = "2.3", features = ["postgres", "uuid", "r2d2", "chrono"] } # Rust ORM: Postgres, UUID, r2d2 pool, chrono
dotenvy = "0.15" # Environment variables
uuid = { version = "1", features = ["v4", "serde"] } # UUID v4 + Serde support
```

### Alternative: project with SQLx + Tokio
```toml
actix-web = "4"
serde = { version = "1.0", features = ["derive"] }
serde_json = "1.0"
sqlx = { version = "0.7", features = ["postgres", "runtime-tokio-native-tls", "macros"] } # SQLx: async/await client, compile-time query validation
tokio = { version = "1", features = ["macros", "rt-multi-thread"] } # Tokio: general-purpose async runtime
dotenvy = "0.15"
```

### 📊 Diesel vs SQLx Comparison Table
| Tool   | Type                    | Strengths                                                                 | Weaknesses                                                  |
|--------|-------------------------|---------------------------------------------------------------------------|-------------------------------------------------------------|
| Diesel | ORM (Object Relational Mapper) | - Strong Rust typing → SQL errors caught at compile time <br> - Expressive Rust API (no need to write raw SQL) <br> - SQL-injection safety | - More rigid (requires `schema.rs`) <br> - Less flexible for complex queries <br> - Not natively async |
| SQLx   | Async/await Client      | - Raw SQL validated at compile time <br> - Full async/await support <br> - Very flexible for advanced SQL | - Not an ORM (no automatic struct/table mapping) <br> - Requires Tokio <br> - Less compile-time safety around mapping |

👉 For a **learning workshop**, we choose **Actix + Diesel**: safety, compile-time guarantees, classic ORM style.


### 🔄 Rust vs Java Analogy (Spring Boot, Hibernate, R2DBC)

For developers coming from the **Java / Spring** ecosystem, here are the equivalences:

| Rust                                 | Java / Spring equivalent                                      |
|--------------------------------------|---------------------------------------------------------------|
| **Actix** (full web framework)       | **Spring Boot MVC** (Tomcat) or **Spring WebFlux** (Reactor Netty) |
| **Tokio** (low-level async runtime)  | **Reactor Netty** (async engine)                              |
| **Diesel** (Rust ORM, compile-time safety) | **Hibernate / JPA**                                      |
| **SQLx** (async SQL client, not an ORM) | **R2DBC** (reactive DB client, not JPA)                   |

👉 Summary:
- **Actix = Spring Boot** (high-level web framework)
- **Tokio = Reactor Netty** (async engine)
- **Diesel = Hibernate/JPA** (classic ORM)
- **SQLx = R2DBC** (reactive SQL client without an ORM)

This helps quickly map where Rust technologies sit in the Java ecosystem.

## 1. 📦 Structure of a Rust Project

A Rust project is managed by **Cargo**, the official tool:  
- `Cargo.toml` → project configuration file (dependencies, version, etc.)  
- `Cargo.lock` → lockfile that locks dependency versions (generated automatically)  
- `src/main.rs` → application entry point (function `main`)  
- `src/lib.rs` → library (if the project is used as a Rust lib)  
- `target/` → folder generated with compiled binaries  

👉 **Basic commands**:  
```bash
cargo build      # compile the project
cargo run        # compile + run the project
cargo test       # run tests
```

---

## 2. 📂 Structure of this Workshop Project

```
2025-09-30_Rust-101-Diesel/
 ├── Cargo.toml              # Rust dependencies (actix, diesel, etc.)
 ├── src/
 │   ├── main.rs             # Actix server startup
 │   ├── handlers.rs         # REST API (CRUD users)
 │   ├── models.rs           # structs Rust <-> tables SQL
 │   ├── schema.rs           # generated by Diesel (table description)
 │   └── db.rs               # gestion pool Postgres
 ├── migrations/             # Diesel migrations (SQL)
 ├── docker-compose.yml      # stack Docker (API + Postgres)
 ├── Dockerfile              # build image API Rust
 └── README.md               # (ce fichier)
```

---

## 3. 🗄️ Diesel & Migrations


**Additional Notes:**

 **Install `diesel_cli` via Cargo**

> ```bash
> cargo install diesel_cli --no-default-features --features postgres
> ```
This installs the `diesel` command-line tool used for generating and running migrations.

 **Create a `.env` file with your database URL**
> Create a `.env` file at the project root with:

> ```
> DATABASE_URL=postgres://postgres:password@localhost:5432/workshop
> ```
 **What this environment variable is used for:** both Diesel and the application read `DATABASE_URL` to know how to connect to PostgreSQL. Commands like `diesel migration run` and your app at startup use it to establish the DB connection without hardcoding credentials.

Diesel is a Rust ORM that manages tables via **migrations**:  

- Create a migration:
```bash
diesel migration generate create_users
```

- Example `up.sql`: (Warning: Not generated automatically by Diesel)
```sql
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

SELECT diesel_manage_updated_at('users');
```

- Example `down.sql`: (Warning: Not generated automatically by Diesel)
```sql
DROP TABLE users;
```

- Apply all migrations:
```bash
diesel migration run
```

- See the status of migrations:
```bash
SELECT * FROM __diesel_schema_migrations;
```

---

## 4. 🐚 psql Cheatsheet

In the Postgres container:  
```bash
docker exec -it rust-ws-db-1 psql -U postgres -d workshop
```

Useful commands:  
```sql
\l             -- list DBs
\c workshop    -- connect to a DB
\dt            -- list tables
\d users       -- structure of the users table
SELECT * FROM users;  -- view contents
\q             -- quit
```

---

## 5. 🛠️ Setup IntelliJ IDEA

- Install [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/).  
- Install the **Rust** plugin from the IntelliJ Marketplace.  
- Install the **Toml** plugin (often already included).  
- Open the project with IntelliJ → it detects `Cargo.toml`.  
- Run:  
  - `cargo build` from IntelliJ or the integrated terminal.  
  - Debug with a **Run Configuration → Cargo Run**.  

---

## 5bis. 🛠️ Setup VS Code

- Install [Visual Studio Code](https://code.visualstudio.com/).
- Installer the **Rust Analyzer** extension (recommended for autocompletion, type checking and navigation).
- Install the **Even Better TOML** extension for better handling of `Cargo.toml` files.
- Install the **CodeLLDB** extension if you want to debug your Rust code.
- Open the project with VS Code → it will detect `Cargo.toml`.
- Run:
  - `cargo build` from the integrated terminal.
  - `cargo run` to run the application.
  - Debug with a **launch.json** configured for the `crud-workshop` binary.

👉 With VS Code, you get an experience close to JetBrains but lighter.

---

## 6. 🚀 Run the Workshop Project

### 1. Start the Docker stack
```bash
docker compose up --build
```

👉 This launches:  
- `db` → Postgres container (port 5432)  
- `api` → Actix REST server (port 8080)  

### 2. Prepare the DB
```bash
diesel setup
diesel migration run
```

---

## 7. 🌐 Test the API (cURL)

- **Create a user**:
```bash
curl -X POST http://localhost:8080/users   -H "Content-Type: application/json"   -d '{"name": "Alice"}'
```

- **List users**:
```bash
curl http://localhost:8080/users
```

- **Update a user**:
```bash
curl -X PUT http://localhost:8080/users/<UUID>   -H "Content-Type: application/json"   -d '{"name": "Alice Updated"}'
```

- **Delete a user**:
```bash
curl -X DELETE http://localhost:8080/users/<UUID>
```

---

## 8. 🔄 Reset the Workshop Rust API CRUD with Actix / Diesel and Postgres

### Option rapide (via Diesel)
```bash
diesel database reset
```

### Option Docker (wipe complet)
```bash
docker compose down -v
docker compose up --build
diesel setup
diesel migration run
```

👉 Deletes Postgres volumes → fresh database.

---

## 9. 🐳 Docker Commands Recap

- Run:  
```bash
docker compose up --build
```

- Stop:
```bash
docker compose down
```

- View logs:  
```bash
docker compose logs -f
```

- Access the Postgres container:  
```bash
docker exec -it rust-ws-db-1 psql -U postgres -d workshop
```

---

✅ You now have a **turnkey Rust/Actix/Diesel/Postgres project**, with full docs to install, use, test, and reset.  
