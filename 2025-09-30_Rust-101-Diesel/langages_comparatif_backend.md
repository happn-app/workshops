# 🦀 Comparatif des langages backend – Rust, Go, Java, Kotlin

Ce tableau compare objectivement Rust, Go, Java et Kotlin sur les critères clés pour développer des backends modernes.

| Critère                       | **Rust**                      | **Go**                          | **Java**                                                  | **Kotlin**                                 |
| ----------------------------- | ----------------------------- | ------------------------------- | --------------------------------------------------------- | ------------------------------------------ |
| 🧠 **Paradigme**              | Système / fonctionnel         | Procédural / concurrent         | Orienté objet                                             | Orienté objet + fonctionnel                |
| ⚙️ **GC**                     | ❌ Non (ownership)             | ✅ Oui (optimisé)                | ✅ Oui                                                     | ✅ Oui                                      |
| 🧵 **Async / Concurrence**    | ✅ `tokio` (zero-cost)         | ✅ `goroutines`, `channel`       | ✅ `CompletableFuture`, `virtual threads` (depuis Java 21) | ✅ Coroutines (`suspend`)                   |
| 🚀 **Performance**            | 🟢 Très haute (C/C++ level)   | 🟡 Bonne                        | 🟠 Moyenne                                                | 🟠 Moyenne (légèrement meilleure que Java) |
| 🛡️ **Sécurité mémoire**      | ✅ Forte (compile-time)        | ❌ Moyenne (pointeurs non sûrs)  | ❌ Moyenne                                                 | ❌ Moyenne                                  |
| 🧰 **Tooling intégré**        | ✅ Excellent (`cargo`, etc.)   | ✅ Simple (`go build`, `go mod`) | ⚠️ Lourd (Maven/Gradle)                                   | ✅ Moderne avec Gradle & IntelliJ           |
| 🧪 **Tests intégrés**         | ✅ Oui (dans `cargo`)          | ✅ Oui (`go test`)               | ✅ Oui (JUnit)                                             | ✅ Oui (Kotest, JUnit)                      |
| 📚 **Écosystème**             | 🟡 En croissance rapide       | 🟢 Mature                       | 🟢 Énorme                                                 | 🟢 Énorme (compat. Java)                   |
| 🛠️ **Frameworks web**        | `actix-web`, `axum`           | `gin`, `echo`, `fiber`          | `Spring`, `Quarkus`, `Micronaut`                          | `Ktor`, `Spring`, `Exposed`                |
| 📦 **Déploiement**            | ✅ Binaire statique            | ✅ Binaire statique              | ❌ JVM / JAR                                               | ❌ JVM / JAR ou natif via GraalVM           |
| 🧗 **Courbe d’apprentissage** | 🚧 Raide                      | 🟢 Facile                       | 🟠 Moyenne                                                | 🟠 Moyenne                                 |
| 🏆 **Cas d’usage typique**    | Performant, temps réel, infra | Services backend, CLI, outils   | APIs d’entreprise, legacy, JavaEE                         | APIs modernes, Android, serverless         |

---

💬 **Remarques** :

- Rust excelle en performance et sécurité mémoire, idéal pour des traitements intensifs ou temps réel.
- Go reste simple et efficace pour du backend à faible latence, même avec une équipe junior.
- Java/Kotlin sont très puissants dans un contexte d'entreprise avec un écosystème massif.

