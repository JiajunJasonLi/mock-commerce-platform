# Implementation Plan — Phase 1

## 1. Project Setup

### Backend

* Create Spring Boot project.
* Add required dependencies:

    * Spring Web
    * Spring Security
    * Spring Data JPA
    * Spring Session
    * Spring Data Redis
    * PostgreSQL Driver
    * Validation
* Configure application properties.
* Configure PostgreSQL connection.
* Configure Redis connection.

### Frontend

* Create React + TypeScript project.
* Configure Tailwind CSS.
* Configure routing.
* Create initial application layout.

---

## 2. Persistent User Model

* Create `MembershipTier` enum:

    * `REGULAR`
    * `PREMIUM`
    * `SUPREME`
* Create `User` JPA entity.
* Add:

    * id
    * username
    * email
    * passwordHash
    * membershipTier
    * createdAt
    * updatedAt
* Create `UserRepository`.
* Add database constraints for unique email.
* Verify that user data can be persisted to PostgreSQL.

---

## 3. User Registration

### Backend

* Create `RegisterRequest` DTO.
* Add request validation.
* Configure `PasswordEncoder`.
* Create registration service.
* Check whether the email already exists.
* Hash the submitted password.
* Assign the `REGULAR` membership tier.
* Save the user.
* Create registration endpoint:

```http
POST /api/auth/register
```

* Return `201 Created` on success.
* Implement:

    * `400 INVALID_REQUEST`
    * `409 EMAIL_ALREADY_EXISTS`

### Frontend

* Create registration page.
* Create registration form.
* Add username, email, and password fields.
* Submit registration request.
* Display validation and API errors.
* Redirect to login after successful registration.

---

## 4. Session Authentication Infrastructure

* Configure Spring Security.
* Configure Spring Session.
* Configure Redis as the session store.
* Configure session cookie:

    * `HttpOnly`
    * `Secure` in production
    * `SameSite`
* Configure public endpoints:

    * registration
    * login
* Configure protected endpoints.
* Configure unauthorized responses.

---

## 5. User Login

### Backend

* Configure user lookup by email.
* Configure Spring Security authentication.
* Verify passwords through the configured password encoder.
* Create login endpoint:

```http
POST /api/auth/login
```

* Create an authenticated session after successful login.
* Store session data in Redis.
* Return the session cookie.
* Return basic user profile information.
* Return `401 INVALID_CREDENTIALS` for unsuccessful authentication.

### Frontend

* Create login page.
* Create email and password form.
* Submit credentials with cookie support enabled.
* Store returned user profile in frontend application state.
* Redirect to profile after successful login.
* Display authentication errors.

---

## 6. User Profile

### Backend

* Create `UserProfileResponse`.
* Create profile service.
* Retrieve the current authenticated user's identity from Spring Security.
* Retrieve persistent profile data from PostgreSQL.
* Create endpoint:

```http
GET /api/profile
```

* Return:

    * username
    * email
    * membership tier
* Reject unauthenticated requests.

### Frontend

* Create profile page.
* Request `/api/profile` with credentials enabled.
* Display:

    * username
    * email
    * membership tier
* Redirect to login if the user is unauthenticated.

---

## 7. User Logout

### Backend

* Configure logout behavior.
* Invalidate the current server-side session.
* Remove or invalidate the corresponding Redis session.
* Clear the session cookie.
* Support:

```http
POST /api/auth/logout
```

* Return `204 No Content`.

### Frontend

* Add logout action.
* Send logout request with credentials enabled.
* Clear frontend user state.
* Redirect to login.
* Prevent access to protected profile UI after logout.

---

## 8. Error Handling

* Create consistent API error response structure.
* Add global exception handling where appropriate.
* Map validation errors to `400 INVALID_REQUEST`.
* Map duplicate email errors to `409 EMAIL_ALREADY_EXISTS`.
* Map authentication failures to `401 INVALID_CREDENTIALS`.
* Map unauthenticated protected requests to `401 UNAUTHORIZED`.

---

## 9. Testing

Testing implementation will be added as features are completed.

Initial focus:

* Registration service tests.
* Registration API tests.
* Authentication and session integration tests.
* Profile authorization tests.
* Logout session invalidation tests.
* React component tests.
* End-to-end authentication flow.

---

## 10. Phase 1 Completion Criteria

Phase 1 is complete when:

* A user can register.
* Passwords are stored securely as hashes.
* New users receive the `REGULAR` membership tier.
* A registered user can log in.
* A server-side session is created and stored in Redis.
* Protected APIs require authentication.
* An authenticated user can view their own profile.
* A user can log out.
* Logout invalidates the server-side session.
* Relevant automated tests pass.
