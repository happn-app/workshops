# Hot flows: StateFlow, SharedFlow, and `combine`

## Cold vs Hot

| | Cold Flow | Hot Flow |
|---|---|---|
| Execution | starts when collected | runs independently of collectors |
| Multiple collectors | each restarts from the beginning | share the same stream |
| Example | database query | UI state, events |
| RxJava equivalent | cold `Observable` | `Subject` (`BehaviorSubject`, `PublishSubject`) |

## `StateFlow` — reactive state

`StateFlow` always holds a current value and emits whenever it changes.
The RxJava equivalent is `BehaviorSubject` — both replay the latest value to new subscribers.

```kotlin
val itemCount = MutableStateFlow(0)
val promoActive = MutableStateFlow(false)
```

Collectors receive the current value immediately on subscription, then any future updates.

## `combine` — merging multiple flows

`combine` emits a new value **whenever any source flow emits** — the equivalent of RxJava's `combineLatest`.

```kotlin
val cartSummary: Flow<String> = combine(itemCount, promoActive) { count, promo ->
    val promoText = if (promo) " (promo active!)" else ""
    "$count item(s)$promoText"
}
```

Each time `itemCount` or `promoActive` changes, `cartSummary` re-emits with the latest
values of both. Click **Run** to see it react to each update.

## `SharedFlow` — broadcast events

`SharedFlow` is a hot flow with no current value — it broadcasts events to all active
collectors. The RxJava equivalent is `PublishSubject`. Use it for one-shot events
(navigation, errors) that should not be replayed.

```kotlin
val events = MutableSharedFlow<String>()
events.emit("match found")
```
