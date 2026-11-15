# User Service API Testing Report

**Project:** Mutual Fund Investment Platform
**Module:** User Service
**Testing tool:** Postman
**Environment:** Local development
**Date:** 15 November 2026

## 1. Test scenarios and results

| ID | Test scenario | Expected result | Actual result | Status |
|---|---|---|---|---|
| TC-01 | Valid investor registration | 201 Created | 201 Created | PASS |
| TC-02 | Duplicate email registration | 400 Bad Request | 400 Bad Request | PASS |
| TC-03 | Valid investor login | 200 OK | 200 OK | PASS |
| TC-04 | Invalid login credentials | 401 Unauthorized | 401 Unauthorized | PASS |
| TC-05 | Registration with invalid email | 400 Bad Request | 400 Bad Request | PASS |
| TC-06 | Registration with invalid mobile number | 400 Bad Request | 400 Bad Request | PASS |
| TC-07 | Registration with weak password | 400 Bad Request | 400 Bad Request | PASS |
| TC-08 | Registration with missing required fields | 400 Bad Request | 400 Bad Request | PASS |
| TC-09 | Login with missing required fields | 400 Bad Request | 400 Bad Request | PASS |
| TC-10 | JWT generation during successful login | Valid JWT returned | Access token returned | PASS |

## 2. JWT verification

Verified the successful login response:

- Access token: Present
- Token type: Bearer
- Expiration: 900 seconds (15 minutes)
- User ID: Returned
- Investor email and name: Returned
- Role: INVESTOR

The actual JWT was not included in this report for security reasons.

## 3. Error response verification

Verified consistent error response fields:

- errorCode
- message
- status
- timestamp

Verified error scenarios:

- VALIDATION_ERROR
- EMAIL_ALREADY_EXISTS
- UNAUTHORIZED

Validation failures return HTTP 400, duplicate email returns HTTP 400, and invalid credentials return HTTP 401.

## 4. Overall result

All 10 listed API test scenarios passed in Postman.

**Conclusion:** Investor registration, login, input validation, duplicate email handling, JWT generation, and consistent error responses were verified successfully.
