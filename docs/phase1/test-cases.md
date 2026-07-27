# 1. Registration Service

| ID         | Component   | Scenario                | Mock/Input                       | Expected                                                                                         |
|------------|-------------|-------------------------|----------------------------------|--------------------------------------------------------------------------------------------------|
| UT-REG-001 | AuthService | Test valid registration | Normal user input                | User saved successfully, returned the same information as request and MembershipTier = `REGULAR` |
| UT-REG-002 | AuthService | Test duplicate email    | User input with duplicate email  | Duplicate-user exception thrown                                                                  |
| UT-REG-003 | AuthService | Test password hashing   | Normal user input                | Encoder called and encoded password saved                                                        |
