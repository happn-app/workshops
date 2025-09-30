# 🚀 Workshop Rust + Actix + Diesel + Postgres

## 0. 🛠️ Installer Rust et utiliser Cargo

### Installation de Rust
Rust s’installe via **rustup** (le gestionnaire officiel) :
```bash
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
```
Puis recharge ton terminal :
```bash
source $HOME/.cargo/env
```

Vérifie l’installation :
```bash
rustc --version
cargo --version
```

### Cargo : gestionnaire de projet Rust
Cargo sert à :
- **Gestion des dépendances** (`Cargo.toml`)
- **Compilation** (`cargo build`)
- **Exécution** (`cargo run`)
- **Tests** (`cargo test`)
- **Documentation** (`cargo doc --open`)
- **Audit** (`cargo audit` si installé)
- **Formatage** (`cargo fmt`)
- **Qualité** (`cargo clippy`)

### Fichiers typiques
- `Cargo.toml` : config projet
- `Cargo.lock` : versions exactes
- `src/main.rs` : point d’entrée
- `src/lib.rs` : lib (optionnel)
- `target/` : binaires compilés

### Flux typique
```bash
cargo new hello-rust
cd hello-rust
cargo build
cargo run
cargo test
cargo fmt
cargo clippy
```

### Dépendances du projet (Diesel + Actix)
```toml
actix-web = "4"                          # Framework web async basé sur Actix
actix-rt = "2"                           # Runtime léger basé sur Actix
serde = { version = "1", features = ["derive"] }  # JSON <-> Rust structs
serde_json = "1"                         # Gestion JSON
diesel = { version = "2.3", features = ["postgres", "uuid", "r2d2", "chrono"] }
# ORM Rust : Postgres, UUID, pool r2d2, chrono
dotenvy = "0.15"                         # Variables d’environnement
uuid = { version = "1", features = ["v4", "serde"] }  
# UUID v4 + support Serde
```

### Alternative : projet avec SQLx + Tokio
```toml
actix-web = "4"
serde = { version = "1.0", features = ["derive"] }
serde_json = "1.0"
sqlx = { version = "0.7", features = ["postgres", "runtime-tokio-native-tls", "macros"] }
# SQLx : client async/await, validation compile-time des requêtes
tokio = { version = "1", features = ["macros", "rt-multi-thread"] }
# Tokio : runtime async généraliste
dotenvy = "0.15"
```

### 📊 Tableau comparatif Diesel vs SQLx
| Outil   | Type        | Points forts                                                                 | Points faibles                                        |
|---------|-------------|-------------------------------------------------------------------------------|------------------------------------------------------|
| Diesel  | ORM (Object Relational Mapper) | - Typage fort Rust → erreurs SQL détectées à la compilation <br> - API expressive en Rust (pas besoin d'écrire du SQL brut) <br> - Sécurité injection SQL | - Plus rigide (schema.rs obligatoire) <br> - Moins flexible pour requêtes complexes <br> - Pas nativement async |
| SQLx    | Client async/await | - SQL brut validé à la compilation <br> - Support async/await complet <br> - Très flexible pour SQL avancé | - Pas d’ORM (pas de mapping struct/table auto) <br> - Nécessite Tokio <br> - Moins de compile-time safety sur mapping |

👉 Pour un **workshop pédagogique**, on choisit **Actix + Diesel** : sécurité, compile-time safety, style ORM classique.


### 🔄 Analogie Rust vs Java (Spring Boot, Hibernate, R2DBC)

Pour les développeurs venant de l’écosystème **Java / Spring**, voici les équivalences :

| Rust                | Java / Spring équivalent                       |
|---------------------|------------------------------------------------|
| **Actix** (framework web complet) | **Spring Boot MVC** (Tomcat) ou **Spring WebFlux** (Reactor Netty) |
| **Tokio** (runtime async bas niveau) | **Reactor Netty** (moteur async) |
| **Diesel** (ORM Rust, compile-time safety) | **Hibernate / JPA** |
| **SQLx** (client SQL async, pas un ORM) | **R2DBC** (client DB réactif, pas JPA) |

👉 Résumé :  
- **Actix = Spring Boot** (framework web haut niveau)  
- **Tokio = Reactor Netty** (moteur async)  
- **Diesel = Hibernate/JPA** (ORM classique)  
- **SQLx = R2DBC** (client SQL réactif sans ORM)  

Cela permet de comprendre rapidement où se situent les technos Rust dans l’univers Java.



## 1. 📦 Structure d’un projet Rust

Un projet Rust est géré par **Cargo**, l’outil officiel :  
- `Cargo.toml` → fichier de configuration du projet (dépendances, version, etc.)  
- `Cargo.lock` → lockfile qui fige les versions des dépendances (généré automatiquement)  
- `src/main.rs` → point d’entrée de l’application (fonction `main`)  
- `src/lib.rs` → bibliothèque (si le projet est utilisé comme lib Rust)  
- `target/` → dossier généré avec les binaires compilés  

👉 **Commandes de base** :  
```bash
cargo build      # compile le projet
cargo run        # compile + lance le projet
cargo test       # lance les tests
```

---

## 2. 📂 Structure de ce projet Workshop

```
rust-ws/
 ├── Cargo.toml              # dépendances Rust (actix, diesel, etc.)
 ├── src/
 │   ├── main.rs             # démarrage serveur Actix
 │   ├── handlers.rs         # API REST (CRUD users)
 │   ├── models.rs           # structs Rust <-> tables SQL
 │   ├── schema.rs           # généré par Diesel (description tables)
 │   └── db.rs               # gestion pool Postgres
 ├── migrations/             # migrations Diesel (SQL)
 ├── docker-compose.yml      # stack Docker (API + Postgres)
 ├── Dockerfile              # build image API Rust
 └── README.md               # (ce fichier)
```

---

## 3. 🗄️ Diesel & Migrations

Diesel est un ORM Rust qui gère les tables via **migrations** :  

- Créer une migration :  
```bash
diesel migration generate create_users
```

- Exemple `up.sql` :
```sql
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

SELECT diesel_manage_updated_at('users');
```

- Exemple `down.sql` :
```sql
DROP TABLE users;
```

- Appliquer toutes les migrations :  
```bash
diesel migration run
```

- Voir l’état des migrations :  
```bash
SELECT * FROM __diesel_schema_migrations;
```

---

## 4. 🐚 Cheatsheet psql

Dans le conteneur Postgres :  
```bash
docker exec -it rust-ws-db-1 psql -U postgres -d workshop
```

Commandes utiles :  
```sql
\l             -- lister les DB
\c workshop    -- se connecter à une DB
\dt            -- lister les tables
\d users       -- structure de la table users
SELECT * FROM users;  -- voir le contenu
\q             -- quitter
```

---

## 5. 🛠️ Setup IntelliJ IDEA

- Installer [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/).  
- Installer le plugin **Rust** depuis le marketplace IntelliJ.  
- Installer le plugin **Toml** (souvent déjà inclus).  
- Ouvrir le projet avec IntelliJ → il détecte `Cargo.toml`.  
- Lancer :  
  - `cargo build` depuis IntelliJ ou terminal intégré.  
  - Débugger avec un **Run Configuration → Cargo Run**.  

---

## 5bis. 🛠️ Setup VS Code

- Installer [Visual Studio Code](https://code.visualstudio.com/).
- Installer l’extension **Rust Analyzer** (recommandée pour l’autocomplétion, le type checking et la navigation).
- Installer l’extension **Even Better TOML** pour une meilleure gestion des fichiers `Cargo.toml`.
- Installer l’extension **CodeLLDB** si tu veux déboguer ton code Rust.
- Ouvrir le projet avec VS Code → il détectera `Cargo.toml`.
- Lancer :
  - `cargo build` depuis le terminal intégré.
  - `cargo run` pour exécuter l’application.
  - Déboguer avec un **launch.json** configuré sur le binaire `crud-workshop`.

👉 Avec VS Code, tu as une expérience proche de JetBrains mais plus légère.

---

## 6. 🚀 Lancer le projet Workshop

### 1. Lancer la stack Docker
```bash
docker compose up --build
```

👉 Cela lance :  
- `db` → conteneur Postgres (port 5432)  
- `api` → serveur Actix REST (port 8080)  

### 2. Préparer la DB
```bash
diesel setup
diesel migration run
```

---

## 7. 🌐 Tester l’API (cURL)

- **Créer un utilisateur** :
```bash
curl -X POST http://localhost:8080/users   -H "Content-Type: application/json"   -d '{"name": "Alice"}'
```

- **Lister les utilisateurs** :
```bash
curl http://localhost:8080/users
```

- **Mettre à jour un utilisateur** :
```bash
curl -X PUT http://localhost:8080/users/<UUID>   -H "Content-Type: application/json"   -d '{"name": "Alice Updated"}'
```

- **Supprimer un utilisateur** :
```bash
curl -X DELETE http://localhost:8080/users/<UUID>
```

---

## 8. 🔄 Reset du projet Workshop

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

👉 Supprime les volumes Postgres → DB neuve.

---

## 9. 🐳 Rappel commandes Docker

- Lancer :  
```bash
docker compose up --build
```

- Stopper :  
```bash
docker compose down
```

- Voir les logs :  
```bash
docker compose logs -f
```

- Accéder au conteneur Postgres :  
```bash
docker exec -it rust-ws-db-1 psql -U postgres -d workshop
```

---

✅ Tu as maintenant un **projet Rust/Actix/Diesel/Postgres clé en main**, avec toute la doc pour l’installer, l’utiliser, le tester et le reset.  
