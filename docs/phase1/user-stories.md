# 1. Register a new account
## Story
As a visitor, I want to register an account using my username, email, 
and password so that I can access the rewards platform.

## Acceptance Criteria
* Given the email is not already registered
* When valid registration information is submitted
* Then a new account is created
* And the password is stored as a hash
* And the user is assigned the REGULAR membership tier
* Duplicate email registration is rejected with an appropriate error.

# 2. User login
## Story
As a registered user, I want to log in so that I can access my account.

## Acceptance Criteria
* Valid credentials authenticate successfully.
* Invalid credentials return an error.
* An authenticated session is created.
* The user is redirected to the profile page after successful login.

# 3. User profile viewing
## Story
As an authenticated user, I want to view my own profile so that I can see my account information.

## Acceptance Criteria
* Username is displayed.
* Email is displayed.
* Membership tier is displayed.
* Another user's profile cannot be accessed.
* An unauthenticated user cannot access the profile.

# 4. User logout
## Story
As an authenticated user, I want to logout so that no one else can access my information without logging in again.

## Acceptance Criteria
* The authenticated session is invalidated.
* Accessing a protected page requires authentication again.
* The user is redirected to the login page.