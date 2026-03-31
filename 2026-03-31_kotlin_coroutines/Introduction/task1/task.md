# Coroutines and suspend functions

## The problem with blocking

When a function calls `Thread.sleep`, the thread is **frozen** for the entire duration —
it cannot do anything else until the sleep ends.

```kotlin
fun blockingWork(name: String) {
    println("$name starts  [thread: ${Thread.currentThread().name}]")
    Thread.sleep(1000)
    println("$name ends    [thread: ${Thread.currentThread().name}]")
}
```

## suspend functions: the thread stays free

A function marked `suspend` can **pause** its coroutine without blocking its thread.
During a `delay`, the thread is released back to the runtime.

```kotlin
suspend fun suspendWork(name: String) {
    println("$name starts  [thread: ${Thread.currentThread().name}]")
    delay(1000)
    println("$name ends    [thread: ${Thread.currentThread().name}]")
}
```

`delay` is the coroutine equivalent of `Thread.sleep`. In sequential code, both take the
same wall-clock time — the difference is what happens to the thread while waiting.

## Running the example

Click **Run** and observe the thread names in the output.

In the blocking block, the thread name is just `main`.
In the suspending block, it becomes `main @coroutine#1` — the runtime tracks each
coroutine as a named entity, distinct from the thread it runs on.

## suspend is contagious

A `suspend` function can only be called from another `suspend` function or from inside a
coroutine. The Kotlin compiler enforces this at compile time.

`runBlocking` bridges regular code and the coroutine world. It starts a coroutine and
blocks the calling thread until it completes — only for `main()` and tests.

## What comes next

Because `delay` frees the thread instead of blocking it, the runtime can run multiple
coroutines on the same thread concurrently. The next lesson introduces `launch`, which
starts a second coroutine and makes that concurrency visible directly.
