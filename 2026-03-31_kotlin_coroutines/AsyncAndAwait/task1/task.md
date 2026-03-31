# `async` and `await`

## From `launch` to `async`

`launch` starts a concurrent coroutine but returns no value.
When you need a result, use `async` — it returns a `Deferred<T>`.

| | `launch` | `async` |
|---|---|---|
| Returns | `Job` | `Deferred<T>` |
| Result | none | a value of type `T` |
| Use case | fire & forget | concurrent work with a result |

Call `.await()` on a `Deferred` to retrieve its value. Like `join()`, it suspends the
calling coroutine without blocking the thread.

## Sequential vs parallel

```kotlin
suspend fun sequential() = coroutineScope {
    val a = fetchA()   // waits ~600ms
    val b = fetchB()   // then waits ~600ms
    Pair(a, b)
}

suspend fun parallel() = coroutineScope {
    val deferredA = async { fetchA() }
    val deferredB = async { fetchB() }
    Pair(deferredA.await(), deferredB.await())
}
```

Click **Run** to see the timing difference:

```
sequential: ~1200ms
parallel:   ~600ms
```

Both `fetchA` and `fetchB` are independent — there is no reason to wait for one before
starting the other. `async` starts both immediately; `.await()` collects the results once
both are ready.

## The sequential trap

Calling `.await()` immediately after `async` defeats the purpose:

```kotlin
// ❌ still sequential — fetchB does not start until fetchA is done
val a = async { fetchA() }.await()
val b = async { fetchB() }.await()
```

Always declare all `async` blocks first, then collect with `.await()`.
