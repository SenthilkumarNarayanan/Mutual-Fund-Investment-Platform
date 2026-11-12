\# Git Branching Strategy and Workflow



\## 1. Objective



Define a standardized Git branching strategy and development workflow for the Mutual Fund Investment Platform (MFIP).



\## 2. Main Branch Strategy



The project follows a simplified GitFlow branching strategy.



\### main



\- Contains stable, production-ready code.

\- Direct commits are not permitted.

\- Changes must be merged through pull requests.

\- Only tested and approved changes are merged into main.



\### develop



\- Serves as the primary integration branch.

\- Contains completed and tested development features.

\- Feature and bug-fix branches are merged into develop through pull requests.

\- Release preparation starts from develop.



\## 3. Feature Branch Convention



Naming format:



`feature/MFIP-XX-short-description`



Example:



`feature/MFIP-10-user-registration`



Rules:

\- Create feature branches from develop.

\- Include the Jira issue key.

\- Use lowercase words separated by hyphens.

\- Merge completed features into develop through pull requests.



\## 4. Bug-Fix Branch Convention



Naming format:



`bugfix/MFIP-XX-short-description`



Example:



`bugfix/MFIP-11-login-error`



Rules:

\- Create bug-fix branches from develop.

\- Include the Jira issue key.

\- Describe the issue using lowercase hyphen-separated words.

\- Merge fixes into develop through pull requests.



\## 5. Hotfix Branch Convention



Naming format:



`hotfix/MFIP-XX-short-description`



Example:



`hotfix/MFIP-12-payment-failure`



Rules:

\- Create hotfix branches from main.

\- Use only for urgent production issues.

\- Merge approved fixes into both main and develop.



\## 6. Release Branch Convention



Naming format:



`release/version`



Example:



`release/1.0.0`



Rules:

\- Create release branches from develop.

\- Use them for final testing, stabilization and release preparation.

\- Merge approved releases into main and back into develop.



\## 7. Commit Message Convention



Format:



`MFIP-XX: Short description`



Examples:



\- `MFIP-10: Add investor registration API`

\- `MFIP-11: Fix login validation`

\- `MFIP-12: Add payment error handling`



Rules:

\- Include the Jira issue key.

\- Use a short, meaningful description.

\- Start the description with an action verb where possible.

\- Avoid vague messages such as `update`, `changes`, or `fix`.



\## 8. Pull Request Guidelines



1\. Create a pull request from the feature or bug-fix branch into develop.

2\. Include the Jira issue key in the PR title.

3\. Explain the purpose and implementation details.

4\. Link the corresponding Jira issue.

5\. Include relevant testing details.

6\. Ensure the project builds successfully.

7\. Request at least one reviewer.

8\. Resolve all review comments before merging.

9\. Do not merge pull requests with unresolved critical issues.

10\. Delete the source branch after successful merging.



For production releases, create a pull request from release into main and merge the release changes back into develop.



\## 9. Java Coding Standards



\### Naming conventions



\- Packages: lowercase (`com.mfplatform.user`)

\- Classes: PascalCase (`UserService`)

\- Interfaces: PascalCase (`UserRepository`)

\- Methods: camelCase (`findUserById`)

\- Variables: camelCase (`userName`)

\- Constants: uppercase with underscores (`MAX\_RETRY\_COUNT`)



\### General guidelines



\- Use meaningful class, method and variable names.

\- Follow consistent Java formatting and indentation.

\- Keep methods focused on a single responsibility.

\- Avoid duplicate code.

\- Use appropriate access modifiers.

\- Prefer constructor injection over field injection.

\- Use Java generics where appropriate.

\- Use `final` for values that should not be reassigned.

\- Handle exceptions appropriately.

\- Use SLF4J for application logging.

\- Avoid printing application logs using `System.out.println()`.



\## 10. Spring Boot Coding Standards



\### Layered architecture



Organize application code into:



\- controller: Handles HTTP requests and responses.

\- service: Contains business logic.

\- repository: Handles database operations.

\- entity: Represents database entities.

\- dto: Defines request and response objects.

\- config: Contains application configuration.

\- exception: Contains application-specific exceptions.



\### REST API guidelines



\- Use appropriate HTTP methods: GET, POST, PUT, PATCH and DELETE.

\- Use meaningful REST endpoint names.

\- Return appropriate HTTP status codes.

\- Validate incoming request data using Bean Validation.

\- Use DTOs instead of exposing JPA entities directly.

\- Centralize exception handling using `@RestControllerAdvice`.



\### Database guidelines



\- Use Spring Data JPA repositories.

\- Define entity relationships appropriately.

\- Avoid unnecessary database queries.

\- Use pagination for large result sets.

\- Externalize database credentials through environment variables.



\### Logging guidelines



\- Use the shared `common-logging` library.

\- Include correlation IDs in request logging.

\- Never log passwords, tokens or sensitive investor information.



\## 11. Security Guidelines



\- Never commit passwords, API keys or secret configuration files.

\- Keep environment-specific credentials outside Git.

\- Validate and sanitize incoming data.

\- Avoid exposing sensitive information in API responses and logs.



\## 12. Development Workflow



1\. Start from the latest develop branch.

2\. Create a feature or bug-fix branch.

3\. Implement the required changes.

4\. Test the implementation.

5\. Commit using the agreed commit convention.

6\. Push the branch to GitHub.

7\. Create a pull request into develop.

8\. Complete code review and merge.

9\. Prepare releases through release branches.

10\. Merge approved releases into main and back into develop.



\## 13. Enforcement



All developers contributing to MFIP must follow these branching, commit, pull request and coding conventions.

