# Step 1: builder
# Start from the official Rust image, which already contains cargo, rustc, and the necessary tools
FROM rust:latest as builder
WORKDIR /app

# Install the dependencies needed for compilation:
# - libpq-dev: provides PostgreSQL development headers and library
# - pkg-config: allows the compiler to correctly locate the libraries
# Then clean the apt cache to reduce the image size
RUN apt-get update && apt-get install -y libpq-dev pkg-config && rm -rf /var/lib/apt/lists/*

# Copy all source code into the image
COPY . .

# Compile the project in release mode (optimized binary)
RUN cargo build --release

# Step 2: runtime
# Use a minimal Debian image to run the compiled binary
FROM debian:bookworm-slim
WORKDIR /app

# Install only the runtime dependencies:
# - libpq5: PostgreSQL shared library needed to run the binary
RUN apt-get update && apt-get install -y libpq5 && rm -rf /var/lib/apt/lists/*

# Copy the compiled binary from the builder stage
COPY --from=builder /app/target/release/rust-101-diesel /usr/local/bin

# Set the container startup command
CMD ["rust-101-diesel"]