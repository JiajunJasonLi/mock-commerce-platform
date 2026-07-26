# Registration
* Each email address can belong to only one account.
* Username and email are required.
* Password is required and must satisfy the defined password policy.
* Passwords must never be stored in plain text.
* Membership tier cannot be selected during registration.

# Authentication
* Only registered users with valid credentials can log in.
* Invalid credentials must not reveal whether the email or password was incorrect.
* An unauthenticated user cannot access protected resources.
* After logout, the previous authenticated state must no longer provide access to protected resources.

# User Profile
* A user can view only their own profile.
* A profile includes username, email, and membership tier.
* Password hashes and other sensitive authentication information must never be returned in the profile response.

# Membership Tier
* The available tiers are REGULAR, PREMIUM, and SUPREME.
* All newly registered users start at REGULAR.