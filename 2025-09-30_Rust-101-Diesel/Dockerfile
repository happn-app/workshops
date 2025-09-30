# Étape 1 : builder
# On part de l’image officielle Rust, qui contient déjà cargo, rustc et les outils nécessaires
FROM rust:latest as builder
WORKDIR /app

# On installe les dépendances nécessaires à la compilation :
# - libpq-dev : fournit les headers et la lib de développement PostgreSQL
# - pkg-config : permet au compilateur de localiser correctement les libs
# On nettoie ensuite la cache apt pour réduire la taille de l’image
RUN apt-get update && apt-get install -y libpq-dev pkg-config && rm -rf /var/lib/apt/lists/*

# On copie tout le code source dans l’image
COPY . .

# On compile le projet en mode release (binaire optimisé)
RUN cargo build --release

# Étape 2 : runtime
# On utilise une image Debian minimale pour exécuter le binaire compilé
FROM debian:bookworm-slim
WORKDIR /app

# On installe uniquement les dépendances d’exécution :
# - libpq5 : bibliothèque partagée PostgreSQL nécessaire pour faire tourner le binaire
RUN apt-get update && apt-get install -y libpq5 && rm -rf /var/lib/apt/lists/*

# On copie le binaire compilé depuis l’étape builder
COPY --from=builder /app/target/release/rust-101-diesel /usr/local/bin

# On définit la commande de démarrage du conteneur
CMD ["rust-101-diesel"]