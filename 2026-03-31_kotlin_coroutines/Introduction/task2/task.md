# Practice: suspend functions

## Task

`fetchProfile` and `fetchScore` are two suspend functions that simulate network calls.

Implement `loadMatch(id: Int): String` so that it:
1. Fetches the profile for the given id
2. Fetches the compatibility score for that profile
3. Returns a string in the format `"$profile ($score%)"`
