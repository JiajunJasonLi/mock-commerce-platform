# Test Plan

## Register
- successful registration
- duplicate email rejected
- invalid input rejected (invalid email, username, password combination)
- password stored as hash
- default tier is REGULAR

## Login
- valid credentials succeed
- wrong password rejected
- unknown email rejected
- session created in Redis
- session cookie returned 

## Logout
- session invalidated
- session cookie cleared
- profile access fails after logout

## Profile
- authenticated user can view profile
- unauthenticated user gets 401
- response contains username, email, membership tier