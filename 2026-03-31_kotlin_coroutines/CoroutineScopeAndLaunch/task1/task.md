# CoroutineScope and `launch`

## launch

`launch` starts a new coroutine that runs **concurrently** with the current one.
It returns a `Job` and does not wait for the result.

```kotlin
val job = launch {
    println("A starts [${Thread.currentThread().name}]")
    delay(1000)
    println("A ends   [${Thread.currentThread().name}]")
}

println("B runs   [${Thread.currentThread().name}]")
delay(500)
println("B done   [${Thread.currentThread().name}]")

job.join()
println("all done")
```

Click **Run** and observe:

```
A starts [main @coroutine#2]
B runs   [main @coroutine#1]
B done   [main @coroutine#1]
A ends   [main @coroutine#2]
all done
```

A and B run on the same thread (`main`) but as separate coroutines.
When A suspends at `delay`, the thread is free — so B runs immediately.
B finishes first (500ms), then waits for A via `job.join()` before printing "all done".

Without `job.join()`, "all done" would print before "A ends".

## Job

`launch` returns a `Job`. Two main uses:

```kotlin
job.join()   // suspend until this coroutine completes
job.cancel() // cancel this coroutine
```

`join()` is an explicit synchronisation point — use it when you need a specific
coroutine to finish before proceeding, while other work continues in parallel.

## Dispatchers

A `Dispatcher` controls which thread (or thread pool) a coroutine runs on.

| Dispatcher | Use case |
|---|---|
| `Dispatchers.Main` | UI thread (Android) |
| `Dispatchers.IO` | Network calls, file I/O |
| `Dispatchers.Default` | CPU-intensive work |

```kotlin
launch(Dispatchers.IO) { /* runs on an IO thread pool */ }
```

With no dispatcher specified, coroutines inherit the dispatcher of their scope.

## coroutineScope

`coroutineScope` creates a child scope inside a `suspend` function and automatically
waits for all coroutines launched inside it — no `join()` needed.

```kotlin
suspend fun doWork() = coroutineScope {
    launch { /* task A */ }
    launch { /* task B */ }
    // suspends here until both A and B complete
}
```

This is the standard pattern for launching concurrent work from a `suspend` function.
