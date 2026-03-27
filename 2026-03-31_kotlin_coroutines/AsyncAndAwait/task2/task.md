# Practice: async/await

## Task

When displaying a match card, the app needs the user's profile and their compatibility score.
Both are fetched independently from separate endpoints.

Implement `loadMatchCard(userId)` so that `fetchProfile` and `fetchCompatibility` run
concurrently. Return `Pair(profile, compatibility)`.
