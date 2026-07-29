# 1. AuthService

| ID         | Component   | Scenario                | Mock/Input                       | Expected                                                                                         |
|------------|-------------|-------------------------|----------------------------------|--------------------------------------------------------------------------------------------------|
| UT-REG-001 | AuthService | Test valid registration | Normal user input                | User saved successfully, returned the same information as request and MembershipTier = `REGULAR` |
| UT-REG-002 | AuthService | Test duplicate email    | User input with duplicate email  | Duplicate-user exception thrown                                                                  |
| UT-REG-003 | AuthService | Test password hashing   | Normal user input                | Encoder called and encoded password saved                                                        |

# 2. AuthController
| ID         | Component      | Scenario                | Mock/Input             | Expected          |
|------------|----------------|-------------------------|------------------------|-------------------|
| CT-REG-001 | AuthController | Test valid registration | Normal user input      | `201 CREATED`     |
| CT-REG-002 | AuthController | Test invalid email      | Invalid email input    | `400 Bad Request` |
| CT-REG-003 | AuthController | Test missing email      | missing email input    | `400 Bad Request` |
| CT-REG-004 | AuthController | Test invalid username   | Invalid username input | `400 Bad Request` |
| CT-REG-005 | AuthController | Test invalid password   | Invalid password input | `400 Bad Request` |
| CT-REG-006 | AuthController | Test duplciate email    | Duplicate email input  | `409 Conflict`    |

