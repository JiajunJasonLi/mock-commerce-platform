# API Design — Phase 1

## General Conventions

Base path:

`/api`

Content type:

`application/json`

All responses use JSON unless the response has no body.

Protected endpoints require valid authentication.

Authentication mechanism:

`To be decided: server-side session or JWT stored in an HttpOnly cookie.`

## Error Response Format

```json
{
  "code": "EMAIL_ALREADY_EXISTS",
  "message": "An account with this email already exists."
}
```

## 1. Register User

### Endpoint
```http
POST /api/auth/register
```

### Description
Creates a new user account

### Request Body
```json
{
  "username": "user",
  "email": "user@example.com",
  "password": "password"
}
```

### Requested Fields
| Field    | Type   | Required  | Description                    |
|----------|--------|:---------:|--------------------------------|
| username | String |    Yes    | User's display name            |
| email    | String |    Yes    | Unique                         |
| password | String |    Yes    | Plain password sent over HTTPS |


### Success Response
`201 Created`
```json
{
  "username": "user"
}
```

### Error Responses
| Status | Code                 | Situation                   |
|--------|----------------------|-----------------------------|
| 400    | INVALID_REQUEST      | Validation fails            |
| 409    | EMAIL_ALREADY_EXISTS | Email is already registered |

## 2. User Login

### Endpoint
```http
POST /api/auth/login
```

### Description
Login user to the application

### Request Body
```json
{
  "email": "user@example.com",
  "password": "password"
}
```

### Requested Fields
| Field    | Type   | Required | Description                    |
|----------|--------|:--------:|--------------------------------|
| email    | String |   Yes    | Unique                         |
| password | String |   Yes    | Plain password sent over HTTPS |

### Success Response
`200 OK` 
```json
{
  "username": "user",
  "email": "user@example.com",
  "membershipTier": "REGULAR"
}
```

### Response Headers
```http
Set-Cookie: SESSION=<session-id>; Max-Age=0; HttpOnly; Secure; SameSite=Lax
```

### Error Responses
| Status | Code                | Situation                      |
|--------|---------------------|--------------------------------|
| 400    | INVALID_REQUEST     | Validation fails               |
| 401    | INVALID_CREDENTIALS | Email or password is incorrect |

## 3. User Logout

### Endpoint
```http
POST /api/auth/logout
```

### Description
Logout user from application

### Request Body
```text
None
```

### Request Headers
```http
Cookie: SESSION=<session-id>
```

### Success Response
`204 No Content`

### Error Responses
| Status | Code         | Situation                             |
|--------|--------------|---------------------------------------|
| 401    | UNAUTHORIZED | No valid authenticated session exists |

## 4. User Profile

### Endpoint
```http
GET /api/profile
```

### Description
Get user profile

### Request Body
```text
None
```

### Request Headers
```http
Cookie: SESSION=<session-id>
```

### Success Response
`200 Ok`
```json
{
  "username": "user",
  "email": "user@example.com",
  "membershipTier": "REGULAR"
}
```

### Error Responses
| Status | Code         | Situation                 |
|--------|--------------|---------------------------|
| 401    | UNAUTHORIZED | User is not authenticated |