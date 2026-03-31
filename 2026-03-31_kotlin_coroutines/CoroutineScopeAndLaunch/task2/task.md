# Practice: launch

## Task

When two users match on the dating app, both must receive a notification.
The notifications are independent — there is no reason to send them one after the other.

Implement `notifyMatch(userId1, userId2)` so that both notifications are sent
**concurrently** using `launch`.
