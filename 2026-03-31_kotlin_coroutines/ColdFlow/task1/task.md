# Flow: cold data streams

## Why Flow?

A `suspend fun` returns **one value**.
A `Flow<T>` emits **multiple values** over time — like an asynchronous sequence.

| | `suspend fun` | `Flow<T>` |
|---|---|---|
| Values returned | 1 | 0..N |
| Execution | eager | lazy |
| RxJava equivalent | `Single` / `Maybe` | cold `Observable` |

## Creating a Flow

The `flow { }` builder emits values using `emit()`:

```kotlin
fun connectionFlow(): Flow<String> = flow {
    val events = listOf("Connecting...", "Authenticating", "Connected", "Disconnected")
    for (event in events) {
        delay(500)
        emit(event)
    }
}
```

## "Cold": lazy by nature

A Flow only runs **when it is collected**. Before that, no code executes.
In RxJava, a cold `Observable` works the same way — each subscriber triggers a fresh execution.

```kotlin
val events = connectionFlow() // nothing runs yet

events.collect { println(it) } // now it runs, one value at a time
```

## Operators

Flows support the same operators as Kotlin collections — `filter`, `map`, `take`, etc.
They are lazy: no extra work happens until collection.

```kotlin
connectionFlow()
    .filter { !it.startsWith("Connecting") }
    .map { "[$it]" }
    .collect { println(it) }

// [Authenticating]
// [Connected]
// [Disconnected]
```
