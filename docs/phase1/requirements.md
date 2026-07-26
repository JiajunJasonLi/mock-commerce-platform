# Overview
A platform for user to redeem product using points.

# Phase 1 Goal
* Allow a new user to create an account
* Allow a registered user to log in
* Protect application resources from unauthenticated users.
* Allow an authenticated user to view their own profile.

# Phase 1 Scope
## User Registration
* A user can register using a username, email, and password.
* Email addresses must be unique.
* Passwords must be securely hashed before being stored.
* A newly registered user receives the default membership tier.

## User Authentication
* A registered user can log in using valid credentials.
* Invalid credentials must return an authentication error.
* The application must maintain the user's authenticated state using the selected authentication approach.
* A user can log out.

## Authorization
* Public endpoints can be accessed without authentication.
* Protected endpoints require authentication.
* An authenticated user cannot access another user's private profile data.
****
## User Profile
An authenticated user can view their own profile, including:
* Username
* Email
* Membership tier

## Membership Tiers
Membership tiers represent customer reward status.

Initial tiers may include:
* REGULAR
* PREMIUM
* SUPREME

All newly registered customers start in the REGULAR tier.

# Acceptance Criteria
* A user can register successfully.
* Duplicate email registration is rejected.
* Passwords are not stored in plain text.
* A registered user can log in with valid credentials.
* Invalid login attempts are rejected.
* Public endpoints remain accessible without authentication.
* Protected endpoints reject unauthenticated users.
* An authenticated user can retrieve their own profile.
* The profile response includes username, email, and membership tier.

# Out of Scope Ideas
* User can change password