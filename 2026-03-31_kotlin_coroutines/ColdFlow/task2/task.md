# Practice: creating and transforming a Flow

## Task

**Part 1**: Implement `likesFlow()` — a `Flow<Int>` that emits user IDs from 1 to 5,
with a 100ms delay between each emission.

**Part 2**: Implement `notificationsFlow()` — a `Flow<String>` that reuses `likesFlow()`
and transforms each id into `"User#$id liked you!"`.
