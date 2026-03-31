# Practice: reactive state with StateFlow

## Task

`nameFlow` and `isOnlineFlow` represent a matched user's current state.

**Part 1**: Implement `profileCardFlow()` — combine both flows to produce
`"$name • Online"` or `"$name • Offline"`.

**Part 2**: In `main()`, update `isOnlineFlow` and `nameFlow` to different values
and observe that the flow re-emits automatically on each change.
